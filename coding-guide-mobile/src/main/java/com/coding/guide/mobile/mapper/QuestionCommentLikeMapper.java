package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionCommentLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 点赞面试题评论映射器
 *
 * @author youzhengjie
 * @date 2022/12/12 23:13:27
 */
@Mapper
@Repository
public interface QuestionCommentLikeMapper extends BaseMapper<QuestionCommentLike> {

}
