package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.ConverUtil;
import com.coding.guide.common.utils.SnowId;
import com.coding.guide.mobile.constant.CaffeineConstant;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.dto.QuestionDTO;
import com.coding.guide.mobile.entity.*;
import com.coding.guide.mobile.mapper.QuestionMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.QuestionBrowseRecordService;
import com.coding.guide.mobile.service.QuestionCollectService;
import com.coding.guide.mobile.service.QuestionLikeService;
import com.coding.guide.mobile.service.QuestionService;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 面试题service impl
 *
 * @author youzhengjie
 * @date 2022/11/09 00:01:04
 */
@Transactional
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    private QuestionMapper questionMapper;

    private QuestionLikeService questionLikeService;

    private QuestionCollectService questionCollectService;

    private QuestionBrowseRecordService questionBrowseRecordService;

    private RedisTemplate redisTemplate;

    /**
     * 拿到面试题的caffeine缓存对象（使用@Qualifier指定注入的bean）
     */
    private Cache<String,Question> questionCache;

    /**
     * redisson客户端
     */
    private RedissonClient redissonClient;

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }
    @Autowired
    public void setQuestionLikeService(QuestionLikeService questionLikeService) {
        this.questionLikeService = questionLikeService;
    }
    @Autowired
    public void setQuestionCollectService(QuestionCollectService questionCollectService) {
        this.questionCollectService = questionCollectService;
    }

    @Autowired
    public void setQuestionBrowseRecordService(QuestionBrowseRecordService questionBrowseRecordService) {
        this.questionBrowseRecordService = questionBrowseRecordService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    @Qualifier("questionCache")
    public void setQuestionCache(Cache<String, Question> questionCache) {
        this.questionCache = questionCache;
    }

    @Autowired
    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public List<Question> selectHottestQuestionByLimit(int page, int size) {
        return questionMapper.selectHottestQuestionByLimit(page,size);
    }

    @Override
    public Question selectQuestionDetail(long id) {
        //使用多级缓存提高性能
        //1：先查JVM本地缓存caffeine,如果有则直接返回。
        Question q = questionCache.get(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX+id, questionId -> {
            //---如果caffeine没有对应缓存的话则会进入这个lambda表达式中---
            //2：如果redis中也没有这道面试题缓存
            final String KEY = RedisConstant.QUESTION_DETAIL_KEY_PREFIX + id;
            if (!redisTemplate.hasKey(KEY)) {
                //3：查询数据库
                Question question = questionMapper.selectQuestionDetail(id);
                //4：将查询出来的数据(question)放进缓存,并设置过期时间（1小时）
                redisTemplate.opsForValue().set(KEY, question, 1, TimeUnit.HOURS);
                //5：此时返回的question对象会自动放入caffeine缓存中。
                return question;
            }
            //6：反之，如果redis中有这道面试题缓存
            else {
                //7：直接从redis中拿到这道面试题的缓存并返回，此时返回的question对象会自动放入caffeine缓存中。
                return (Question) redisTemplate.opsForValue().get(KEY);
            }
        });
        //8：记录用户浏览面试题记录
        questionBrowseRecordService.addQuestionBrowseRecord(id);
        //9：返回结果
        return q;
    }

    @Override
    public List<Question> searchHottestQuestionByKeyWordAndLimit(int page, int size, String keyword) {


        return questionMapper.searchHottestQuestionByKeyWordAndLimit(page, size, keyword);
    }

    @Override
    public long selectQuestionCountByKeyWord(String keyword) {

        return questionMapper.selectQuestionCountByKeyWord(keyword);
    }

    @Override
    public List<Question> searchLatestQuestionByKeyWordAndLimit(int page, int size, String keyword) {

        return questionMapper.searchLatestQuestionByKeyWordAndLimit(page, size, keyword);
    }

    @Override
    public List<Question> searchRecommendQuestionByKeyWordAndLimit(int page, int size, String keyword) {
        return questionMapper.searchRecommendQuestionByKeyWordAndLimit(page, size, keyword);
    }

    @Override
    public long selectRecommendQuestionCountByKeyWord(String keyword) {
        return questionMapper.selectRecommendQuestionCountByKeyWord(keyword);
    }

    @Override
    public List<Question> selectQuestionByTagIdAndLimit(int page, int size, long tagid) {
        return questionMapper.selectQuestionByTagIdAndLimit(page, size, tagid);
    }

    @Override
    public long selectQuestionCountByTagId(long tagid) {
        return questionMapper.selectQuestionCountByTagId(tagid);
    }

    @Override
    public boolean likeQuestion(Long userid, Long questionId) {
        try {
            //判断该用户有没有点赞过这个面试题
            Long count = questionLikeService.lambdaQuery()
                    .eq(QuestionLike::getUserId, userid)
                    .eq(QuestionLike::getQuestionId, questionId).count();
            //如果面试题没有被该用户点赞过，则点赞
            if(count == 0){
                //修改t_question的like_count+1
                questionMapper.incrLikeCount(questionId);
                //添加点赞记录到t_question_like中
                QuestionLike questionLike = QuestionLike.builder()
                        .id(SnowId.nextId())
                        .userId(userid)
                        .questionId(questionId)
                        .likeTime(LocalDateTime.now())
                        .build();
                questionLikeService.save(questionLike);
                //修改本地缓存（caffeine）

                //查询本地缓存
                Question q1 = questionCache.getIfPresent(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX + questionId);
                //如果caffeine有缓存才修改。（没有缓存则不用修改）
                if(q1 != null){
                    //点赞数+1
                    q1.setLikeCount(q1.getLikeCount()+1);
                    //把新缓存放到caffeine中
                    questionCache.put(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX+questionId,q1);
                }
                //修改分布式缓存（Redis）
                Question q2 = (Question) redisTemplate.opsForValue().get(RedisConstant.QUESTION_DETAIL_KEY_PREFIX + questionId);
                if(q2 != null){
                    //点赞数+1
                    q2.setLikeCount(q2.getLikeCount()+1);
                    //把新缓存放到redis中
                    redisTemplate.opsForValue().set(RedisConstant.QUESTION_DETAIL_KEY_PREFIX+questionId,
                            q2, 1, TimeUnit.HOURS);
                }
            }
            //如果面试题被该用户点赞过，则取消点赞
            else {
                //修改t_question的like_count-1
                questionMapper.decrLikeCount(questionId);
                //删除对应t_question_like的点赞记录
                LambdaQueryWrapper<QuestionLike> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(QuestionLike::getUserId, userid)
                        .eq(QuestionLike::getQuestionId, questionId);
                questionLikeService.remove(lambdaQueryWrapper);

                //修改本地缓存（caffeine）

                //查询本地缓存
                Question q1 = questionCache.getIfPresent(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX + questionId);
                //如果caffeine有缓存才修改。（没有缓存则不用修改）
                if(q1 != null){
                    //点赞数-1
                    q1.setLikeCount(q1.getLikeCount()-1);
                    //把新缓存放到caffeine中
                    questionCache.put(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX+questionId,q1);
                }
                //修改分布式缓存（Redis）
                Question q2 = (Question) redisTemplate.opsForValue().get(RedisConstant.QUESTION_DETAIL_KEY_PREFIX + questionId);
                if(q2 != null){
                    //点赞数-1
                    q2.setLikeCount(q2.getLikeCount()+1);
                    //把新缓存放到redis中
                    redisTemplate.opsForValue().set(RedisConstant.QUESTION_DETAIL_KEY_PREFIX+questionId,
                            q2, 1, TimeUnit.HOURS);
                }
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public boolean collectQuestion(Long userid, Long questionId) {
        try {
            //判断该用户有没有收藏过这个面试题
            Long count = questionCollectService.lambdaQuery()
                    .eq(QuestionCollect::getUserId, userid)
                    .eq(QuestionCollect::getQuestionId, questionId).count();
            //如果面试题没有被该用户收藏过，则收藏
            if(count == 0){
                //修改t_question的collect_count+1
                questionMapper.incrCollectCount(questionId);
                //添加收藏记录到t_question_collect中
                QuestionCollect questionCollect = QuestionCollect.builder()
                        .id(SnowId.nextId())
                        .userId(userid)
                        .questionId(questionId)
                        .collectTime(LocalDateTime.now())
                        .build();
                questionCollectService.save(questionCollect);

                //修改本地缓存（caffeine）

                //查询本地缓存
                Question q1 = questionCache.getIfPresent(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX + questionId);
                //如果caffeine有缓存才修改。（没有缓存则不用修改）
                if(q1 != null){
                    //收藏数+1
                    q1.setCollectCount(q1.getCollectCount()+1);
                    //把新缓存放到caffeine中
                    questionCache.put(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX+questionId,q1);
                }
                //修改分布式缓存（Redis）
                Question q2 = (Question) redisTemplate.opsForValue().get(RedisConstant.QUESTION_DETAIL_KEY_PREFIX + questionId);
                if(q2 != null){
                    //收藏数+1
                    q2.setCollectCount(q2.getCollectCount()+1);
                    //把新缓存放到redis中
                    redisTemplate.opsForValue().set(RedisConstant.QUESTION_DETAIL_KEY_PREFIX+questionId,
                            q2, 1, TimeUnit.HOURS);
                }
            }
            //如果面试题被该用户收藏过，则取消收藏
            else {
                //修改t_question的collect_count-1
                questionMapper.decrCollectCount(questionId);
                //删除对应t_question_collect的收藏记录
                LambdaQueryWrapper<QuestionCollect> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(QuestionCollect::getUserId, userid)
                        .eq(QuestionCollect::getQuestionId, questionId);
                questionCollectService.remove(lambdaQueryWrapper);

                //修改本地缓存（caffeine）

                //查询本地缓存
                Question q1 = questionCache.getIfPresent(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX + questionId);
                //如果caffeine有缓存才修改。（没有缓存则不用修改）
                if(q1 != null){
                    //收藏数-1
                    q1.setCollectCount(q1.getCollectCount()-1);
                    //把新缓存放到caffeine中
                    questionCache.put(CaffeineConstant.QUESTION_DETAIL_KEY_PREFIX+questionId,q1);
                }
                //修改分布式缓存（Redis）
                Question q2 = (Question) redisTemplate.opsForValue().get(RedisConstant.QUESTION_DETAIL_KEY_PREFIX + questionId);
                if(q2 != null){
                    //收藏数-1
                    q2.setCollectCount(q2.getCollectCount()-1);
                    //把新缓存放到redis中
                    redisTemplate.opsForValue().set(RedisConstant.QUESTION_DETAIL_KEY_PREFIX+questionId,
                            q2, 1, TimeUnit.HOURS);
                }
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Long> selectAllLikeQuestionIdByUserId(Long userid) {
        return questionLikeService.lambdaQuery()
                .select(QuestionLike::getQuestionId)
                .eq(QuestionLike::getUserId, userid)
                .list()
                //并行流提高效率，因为我们对这个集合元素的顺序没有要求才可以这样用
                .parallelStream()
                .map(QuestionLike::getQuestionId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> selectAllCollectQuestionIdByUserId(Long userid) {
        return questionCollectService.lambdaQuery()
                .select(QuestionCollect::getQuestionId)
                .eq(QuestionCollect::getUserId, userid)
                .list()
                //并行流提高效率，因为我们对这个集合元素的顺序没有要求才可以这样用
                .parallelStream()
                .map(QuestionCollect::getQuestionId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> selectUserPublicQuestionByLimit(Long currentUserId, int page, int size) {
        return questionMapper.selectUserPublicQuestionByLimit(currentUserId, page, size);
    }

    @Override
    public List<Question> selectUserPrivateQuestionByLimit(Long currentUserId, int page, int size) {
        return questionMapper.selectUserPrivateQuestionByLimit(currentUserId, page, size);
    }

    @Override
    public List<Question> selectUserCollectQuestionByLimit(Long currentUserId, int page, int size) {
        return questionMapper.selectUserCollectQuestionByLimit(currentUserId, page, size);
    }

    @Override
    public Long selectUserCollectQuestionCount(Long currentUserId) {
        return questionMapper.selectUserCollectQuestionCount(currentUserId);
    }

    @Override
    public List<Question> selectUserLikeQuestionByLimit(Long currentUserId, int page, int size) {
        return questionMapper.selectUserLikeQuestionByLimit(currentUserId, page, size);
    }

    @Override
    public Long selectUserLikeQuestionCount(Long currentUserId) {
        return questionMapper.selectUserLikeQuestionCount(currentUserId);
    }


    @Override
    public String selectLikedCountByUserId(Long userid) {
        //1：查询出来结果
        Long likedCount = questionMapper.selectLikedCountByUserId(userid);
        //2：转换结果
        return ConverUtil.converCount(likedCount);
    }

    @Override
    public String selectCollectedCountByUserId(Long userid) {
        //1：查询出来结果
        Long collectedCount = questionMapper.selectCollectedCountByUserId(userid);
        //2：转换结果
        return ConverUtil.converCount(collectedCount);
    }

    @Override
    public List<Question> selectUserQuestionBrowseRecordByLimit(Long currentUserId, int page, int size) {

        return questionMapper.selectUserQuestionBrowseRecordByLimit(currentUserId, page, size);
    }

    @Override
    public Long selectUserQuestionBrowseRecordCount(Long currentUserId) {

        return questionMapper.selectUserQuestionBrowseRecordCount(currentUserId);
    }

    @Override
    public ResponseResult<String> publishQuestion(QuestionDTO questionDTO, String accessToken) {

        try {
            ResponseResult<String> responseResult = new ResponseResult<>();
            //锁的key
            final String SAVE_DRAFT_LOCK_KEY = RedisConstant.SAVE_QUESTION_LOCK_KEY_PREFIX + accessToken;
            //获取redisson的锁
            RLock lock = redissonClient.getLock(SAVE_DRAFT_LOCK_KEY);
            //使用redisson实现防止表单重复提交（幂等性），保证5秒內不能重复提交表单。
            //waittime=0（一定要设置成0），说明会立刻判断能不能获取到这把锁（不做任何等待，直接返回获取的结果），如果不能直接返回false，反之则返回true
            //如果waittime不设置成0,在高并发下就会等待锁,一旦得到了锁则会出现表单重复提交问题。
            //leaseTime=5，说明如果能够获取到这把锁，则把这个锁的过期时间设置为5秒。
            boolean getLock = lock.tryLock(0, 5, TimeUnit.SECONDS);
            //获取锁成功，说明可以进行发布内容的操作了
            if (getLock) {
                Long id = SnowId.nextId();
                Long userId = SecurityContext.getCurrentUserId();
                String title = questionDTO.getQuestionTitle();
                String content = questionDTO.getQuestionContent();
                int allowComment = !questionDTO.getAllowComment() ? 0 : 1;
                int difficulty = switch (questionDTO.getDifficulty()) {
                    case "简单":
                        yield 1;
                    case "中等":
                        yield 2;
                    case "较难":
                        yield 3;
                    case "困难":
                        yield 4;
                    default:
                        throw new RuntimeException("difficulty参数异常");
                };
                int isPublic = Integer.parseInt(questionDTO.getIsPublic());
                String tags = questionDTO.getTags();
                Question question = Question.builder()
                        .id(id)
                        .userId(userId)
                        .title(title)
                        .content(content)
                        .allowComment(allowComment)
                        .recommend(0)
                        .isPublic(isPublic)
                        .readCount(0)
                        .likeCount(0)
                        .collectCount(0)
                        .commentCount(0)
                        .meetCount(0)
                        .difficulty(difficulty)
                        .tags(tags)
                        .publishTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .sort(1)
                        .delFlag(0)
                        .build();
                boolean saveSuccess = this.save(question);
                if(saveSuccess){
                    responseResult.setCode(ResponseType.SUCCESS.getCode())
                            .setMsg("发布内容成功");
                }else {
                    responseResult.setCode(ResponseType.ERROR.getCode())
                            .setMsg("发布内容失败");
                }
                return responseResult;
            }
            //获取锁失败,防止重复提交表单处理
            responseResult.setCode(ResponseType.ERROR.getCode())
                    .setMsg("请不要短时间内重复发布内容");
            return responseResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

}
