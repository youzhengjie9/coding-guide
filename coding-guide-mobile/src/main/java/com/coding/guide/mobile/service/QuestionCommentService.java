package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.dto.QuestionCommentDTO;
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
     * 写面试题的评论
     *
     * @param questionCommentDTO 面试题评论dto
     * @return {@link QuestionCommentVO}
     */
    QuestionCommentVO writeQuestionComment(QuestionCommentDTO questionCommentDTO);

    /**
     * 获取用户点赞的所有面试题评论id集合
     *
     * @param userid 用户id
     * @return {@link List}<{@link Long}>
     */
    List<Long> selectAllLikeQuestionCommentIdByUserId(Long userid);


    /**
     * 点赞面试题的评论（如果该条面试题评论没有被该用户点赞过，则点赞，反之则取消点赞）
     *
     * @param userid    用户id
     * @param commentId 评论id
     * @return boolean
     */
    boolean likeQuestionComment(Long userid, Long commentId);

}
