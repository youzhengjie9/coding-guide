package com.coding.guide.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.entity.Question;
import com.coding.guide.mapper.QuestionMapper;
import com.coding.guide.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public List<Question> selectHottestQuestionByLimit(int page, int size) {
        return questionMapper.selectHottestQuestionByLimit(page,size);
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

}
