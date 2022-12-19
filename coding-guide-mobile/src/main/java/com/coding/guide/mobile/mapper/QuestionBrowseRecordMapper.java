package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionBrowseRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 面试题浏览记录映射器
 *
 * @author youzhengjie
 * @date 2022/12/18 17:44:49
 */
@Mapper
@Repository
public interface QuestionBrowseRecordMapper extends BaseMapper<QuestionBrowseRecord> {

}
