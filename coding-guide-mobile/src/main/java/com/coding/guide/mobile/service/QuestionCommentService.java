package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.WriteQuestionCommentDTO;
import com.coding.guide.mobile.entity.QuestionComment;
import com.coding.guide.mobile.vo.QuestionCommentVO;

import java.util.List;

/**
 * 面试题评论服务
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:47
 */
public interface QuestionCommentService extends IService<QuestionComment> {

    /**
     * 根据面试题id分页查询评论列表VO
     *
     * @param questionId 面试题id
     * @param page       页面
     * @param size       大小
     * @return {@link List}<{@link QuestionCommentVO}>
     */
    List<QuestionCommentVO> selectListVoByQuestionIdAndLimit(long questionId, int page, int size);


    /**
     * 根据面试题id查询评论总数
     *
     * @param questionId 面试题id
     * @return long
     */
    long selectCountByQuestionId(long questionId);


    /**
     * 写面试题评论
     *
     * @param writeQuestionCommentDTO 写面试题评论dto
     * @return {@link QuestionCommentVO}
     */
    QuestionCommentVO writeQuestionComment(WriteQuestionCommentDTO writeQuestionCommentDTO);

}
