package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionCollect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 收藏面试题映射器
 *
 * @author youzhengjie
 * @date 2022/11/22 23:26:41
 */
@Mapper
@Repository
public interface QuestionCollectMapper extends BaseMapper<QuestionCollect> {


}
