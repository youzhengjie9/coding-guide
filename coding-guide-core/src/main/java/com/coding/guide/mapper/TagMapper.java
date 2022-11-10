package com.coding.guide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 标签mapper
 *
 * @author youzhengjie
 * @date 2022/11/10 14:52:55
 */
@Mapper
@Repository
public interface TagMapper extends BaseMapper<Tag> {



}
