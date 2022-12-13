package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionComment;
import com.coding.guide.mobile.vo.QuestionCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 面试题评论映射器
 *
 * @author youzhengjie
 * @date 2022/12/04 17:51:17
 */
@Mapper
@Repository
public interface QuestionCommentMapper extends BaseMapper<QuestionComment> {

    /**
     * 根据面试题id分页查询评论列表VO
     *
     * @param questionId 面试题id
     * @param page       页面
     * @param size       大小
     * @return {@link List}<{@link QuestionComment}>
     */
    List<QuestionCommentVO> selectListVoByQuestionIdAndLimit(@Param("questionId") long questionId,
                                                             @Param("page") int page,
                                                             @Param("size") int size);

    /**
     * 根据面试题id查询评论总数
     *
     * @param questionId 面试题id
     * @return long
     */
    long selectCountByQuestionId(@Param("questionId") long questionId);

    /**
     * 修改t_question_comment表对应的评论点赞数+1
     *
     * @param commentId 评论id
     * @return int
     */
    int incrLikeCount(@Param("commentId") Long commentId);


    /**
     * 修改t_question_comment表对应的评论点赞数-1
     *
     * @param commentId 评论id
     * @return int
     */
    int decrLikeCount(@Param("commentId") Long commentId);

}
