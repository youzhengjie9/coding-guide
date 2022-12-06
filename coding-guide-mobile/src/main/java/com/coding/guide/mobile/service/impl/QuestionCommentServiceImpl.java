package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.entity.QuestionComment;
import com.coding.guide.mobile.mapper.QuestionCommentMapper;
import com.coding.guide.mobile.service.QuestionCommentService;
import com.coding.guide.mobile.vo.QuestionCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面试题评论服务impl
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:57
 */
@Service
public class QuestionCommentServiceImpl extends ServiceImpl<QuestionCommentMapper, QuestionComment> implements QuestionCommentService {

    private QuestionCommentMapper questionCommentMapper;

    @Autowired
    public void setQuestionCommentMapper(QuestionCommentMapper questionCommentMapper) {
        this.questionCommentMapper = questionCommentMapper;
    }

    @Override
    public List<QuestionCommentVO> selectListVoByQuestionIdAndLimit(long questionId, int page, int size) {
        page=(page-1)*size;

        return questionCommentMapper.selectListVoByQuestionIdAndLimit(questionId, page, size);
    }

    @Override
    public long selectCountByQuestionId(long questionId) {

        return questionCommentMapper.selectCountByQuestionId(questionId);
    }
}
