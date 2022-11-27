package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.mobile.entity.IntegralLevel;
import com.coding.guide.mobile.mapper.IntegralLevelMapper;
import com.coding.guide.mobile.service.IntegralLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 积分等级服务impl
 *
 * @author youzhengjie
 * @date 2022/11/27 22:58:36
 */
@Service
public class IntegralLevelServiceImpl extends ServiceImpl<IntegralLevelMapper, IntegralLevel> implements IntegralLevelService {

    @Autowired
    private IntegralLevelMapper integralLevelMapper;

    @Override
    public IntegralLevel getIntegralLevelByIntegral(int integral) {

        return integralLevelMapper.getIntegralLevelByIntegral(integral);
    }
}
