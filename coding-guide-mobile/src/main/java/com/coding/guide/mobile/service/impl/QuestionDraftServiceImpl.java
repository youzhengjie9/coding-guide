package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.SnowId;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.dto.QuestionDTO;
import com.coding.guide.mobile.entity.QuestionDraft;
import com.coding.guide.mobile.mapper.QuestionDraftMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.QuestionDraftService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 面试题草稿服务impl
 *
 * @author youzhengjie
 * @date 2022/12/23 16:04:27
 */
@Service
public class QuestionDraftServiceImpl extends ServiceImpl<QuestionDraftMapper, QuestionDraft> implements QuestionDraftService {

    private RedisTemplate redisTemplate;

    /**
     * redisson客户端
     */
    private RedissonClient redissonClient;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public ResponseResult<String> saveDraft(QuestionDTO questionDTO , String accessToken) {

        try {
            ResponseResult<String> responseResult = new ResponseResult<>();
            //锁的key
            final String SAVE_DRAFT_LOCK_KEY = RedisConstant.SAVE_QUESTION_DRAFT_LOCK_KEY_PREFIX + accessToken;
            //获取redisson的锁
            RLock lock = redissonClient.getLock(SAVE_DRAFT_LOCK_KEY);
            //使用redisson实现防止表单重复提交（幂等性），保证5秒內不能重复提交表单。
            //waittime=0（一定要设置成0），说明会立刻判断能不能获取到这把锁（不做任何等待，直接返回获取的结果），如果不能直接返回false，反之则返回true
            //如果waittime不设置成0,在高并发下就会等待锁,一旦得到了锁则会出现表单重复提交问题。
            //leaseTime=5，说明如果能够获取到这把锁，则把这个锁的过期时间设置为5秒。
            boolean getLock = lock.tryLock(0, 5, TimeUnit.SECONDS);
            //获取锁成功，说明可以进行保存草稿的操作了
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
                QuestionDraft questionDraft = QuestionDraft.builder()
                        .id(id)
                        .userId(userId)
                        .title(title)
                        .content(content)
                        .allowComment(allowComment)
                        .isPublic(isPublic)
                        .difficulty(difficulty)
                        .tags(tags)
                        .saveTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .delFlag(0)
                        .build();
                boolean saveSuccess = this.save(questionDraft);
                if(saveSuccess){
                    responseResult.setCode(ResponseType.SUCCESS.getCode())
                            .setMsg("保存草稿成功");
                }else {
                    responseResult.setCode(ResponseType.ERROR.getCode())
                            .setMsg("保存草稿失败");
                }
                return responseResult;
            }
            //获取锁失败,防止重复提交表单处理
            responseResult.setCode(ResponseType.ERROR.getCode())
                    .setMsg("请不要短时间内重复保存草稿");
            return responseResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
}
