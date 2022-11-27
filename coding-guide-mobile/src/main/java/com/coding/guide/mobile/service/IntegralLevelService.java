package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.entity.IntegralLevel;

/**
 * 积分等级服务
 *
 * @author youzhengjie
 * @date 2022/11/27 22:57:28
 */
public interface IntegralLevelService extends IService<IntegralLevel> {

    /**
     * 通过积分查询对应的积分等级
     *
     * @param integral 积分
     * @return {@link IntegralLevel}
     */
    IntegralLevel getIntegralLevelByIntegral(int integral);


}
