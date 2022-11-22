package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.entity.Question;
import com.coding.guide.mobile.mapper.QuestionMapper;
import com.coding.guide.mobile.service.QuestionService;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 面试题service impl
 *
 * @author youzhengjie
 * @date 2022/11/09 00:01:04
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //拿到面试题的caffeine缓存对象（使用@Qualifier指定注入的bean）
    @Autowired
    @Qualifier("questionCache")
    private Cache<Long,Question> questionCache;

    @Override
    public List<Question> selectHottestQuestionByLimit(int page, int size) {
        return questionMapper.selectHottestQuestionByLimit(page,size);
    }

    @Override
    public Question selectQuestionDetail(long id) {
        //使用多级缓存提高性能
        //1：先查JVM本地缓存caffeine,如果有则直接返回。
        Question q = questionCache.get(id, questionId -> {
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
        //8：返回结果
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

}
