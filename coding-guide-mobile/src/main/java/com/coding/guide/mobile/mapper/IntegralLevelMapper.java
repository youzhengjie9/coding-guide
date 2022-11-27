package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.IntegralLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 积分等级映射器
 *
 * @author youzhengjie
 * @date 2022/11/27 22:56:45
 */
@Mapper
@Repository
public interface IntegralLevelMapper extends BaseMapper<IntegralLevel> {

    /**
     * 通过积分查询对应的积分等级
     *
     * @param integral 积分
     * @return {@link IntegralLevel}
     */
    IntegralLevel getIntegralLevelByIntegral(@Param("integral") int integral);


}
