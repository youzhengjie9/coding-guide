package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionDraft;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 面试题草稿映射器
 *
 * @author youzhengjie
 * @date 2022/12/23 15:57:00
 */
@Mapper
@Repository
public interface QuestionDraftMapper extends BaseMapper<QuestionDraft> {



}
