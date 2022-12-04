package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 面试题评论映射器
 *
 * @author youzhengjie
 * @date 2022/12/04 17:51:17
 */
@Mapper
@Repository
public interface QuestionCommentMapper extends BaseMapper<QuestionComment> {


}
