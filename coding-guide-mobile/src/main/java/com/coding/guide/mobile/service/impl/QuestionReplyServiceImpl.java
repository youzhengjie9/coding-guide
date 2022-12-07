package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.mobile.entity.QuestionReply;
import com.coding.guide.mobile.mapper.QuestionReplyMapper;
import com.coding.guide.mobile.service.QuestionReplyService;
import com.coding.guide.mobile.vo.QuestionReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面试题回复服务impl
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:33
 */
@Service
public class QuestionReplyServiceImpl extends ServiceImpl<QuestionReplyMapper, QuestionReply> implements QuestionReplyService {

    private QuestionReplyMapper questionReplyMapper;

    @Autowired
    public void setQuestionReplyMapper(QuestionReplyMapper questionReplyMapper) {
        this.questionReplyMapper = questionReplyMapper;
    }

    @Override
    public List<QuestionReplyVO> selectListVoByCommentIdAndLimit(long commentId, int page, int size) {
        page=(page-1)*size;

        return questionReplyMapper.selectListVoByCommentIdAndLimit(commentId, page, size);
    }

    @Override
    public long selectCountByCommentId(long commentId) {

        return questionReplyMapper.selectCountByCommentId(commentId);
    }
}
