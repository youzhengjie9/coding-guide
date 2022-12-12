package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.dto.QuestionReplyDTO;
import com.coding.guide.mobile.entity.QuestionReply;
import com.coding.guide.mobile.vo.QuestionReplyVO;

import java.util.List;
import java.util.Map;

/**
 * 面试题回复服务
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:41
 */
public interface QuestionReplyService extends IService<QuestionReply> {


    /**
     * 根据评论id分页查询面试题回复列表VO和一级回复实际个数的Map
     *
     * @param commentId 评论id
     * @param page      页面
     * @param size      大小
     * @return {@link Map}<{@link String},{@link Object}>
     */
    Map<String,Object> selectMapByCommentIdAndLimit(long commentId, int page, int size);


    /**
     * 根据评论id查询所有replied_id!=0的面试题回复列表VO（二级/多级回复）
     *
     * @param commentId 评论id
     * @return long
     */
    long selectCountByReplyIdEq0AndCommentId(long commentId);

    /**
     * 写面试题的回复
     *
     * @param questionReplyDTO 面试题回复dto
     * @return {@link QuestionReplyVO}
     */
    QuestionReplyVO writeQuestionReply(QuestionReplyDTO questionReplyDTO);


}
