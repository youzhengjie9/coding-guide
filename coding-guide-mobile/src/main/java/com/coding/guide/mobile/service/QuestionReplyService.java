package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.entity.QuestionReply;
import com.coding.guide.mobile.vo.QuestionReplyVO;

import java.util.List;

/**
 * 面试题回复服务
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:41
 */
public interface QuestionReplyService extends IService<QuestionReply> {


    /**
     * 根据评论id分页查询面试题回复列表VO
     *
     * @param commentId 评论id
     * @param page      页面
     * @param size      大小
     * @return {@link List}<{@link QuestionReplyVO}>
     */
    List<QuestionReplyVO> selectListVoByCommentIdAndLimit(long commentId, int page, int size);


    /**
     * 根据评论id查询回复总数
     *
     * @param commentId 评论id
     * @return long
     */
    long selectCountByCommentId(long commentId);

}
