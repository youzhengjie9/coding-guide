package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 点赞面试题映射器
 *
 * @author youzhengjie
 * @date 2022/11/20 22:28:41
 */
@Mapper
@Repository
public interface QuestionLikeMapper extends BaseMapper<QuestionLike> {

}
