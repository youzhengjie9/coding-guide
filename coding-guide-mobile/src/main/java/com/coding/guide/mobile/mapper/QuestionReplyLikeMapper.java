package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionReplyLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 点赞面试题回复映射器
 *
 * @author youzhengjie
 * @date 2022/12/14 17:48:05
 */
@Mapper
@Repository
public interface QuestionReplyLikeMapper extends BaseMapper<QuestionReplyLike> {

}
