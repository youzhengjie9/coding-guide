package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionReply;
import com.coding.guide.mobile.vo.QuestionReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 面试题回复映射器
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:06
 */
@Mapper
@Repository
public interface QuestionReplyMapper extends BaseMapper<QuestionReply> {

    /**
     * 根据评论id分页查询面试题回复列表VO
     *
     * @param commentId 评论id
     * @param page      页面
     * @param size      大小
     * @return {@link List}<{@link QuestionReplyVO}>
     */
    List<QuestionReplyVO> selectListVoByCommentIdAndLimit(@Param("commentId") long commentId,
                                                          @Param("page") int page,
                                                          @Param("size") int size);

    /**
     * 根据评论id查询回复总数
     *
     * @param commentId 评论id
     * @return long
     */
    long selectCountByCommentId(@Param("commentId") long commentId);

}
