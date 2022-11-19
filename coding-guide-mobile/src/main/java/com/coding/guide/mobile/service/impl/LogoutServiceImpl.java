package com.coding.guide.mobile.service.impl;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.JwtUtil;
import com.coding.guide.mobile.service.LogoutService;
import com.coding.guide.mobile.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 退出登录服务impl
 *
 * @author youzhengjie
 * @date 2022/11/19 15:04:25
 */
@Service
@Slf4j
public class LogoutServiceImpl implements LogoutService {

    @Override
    public ResponseResult logout(TokenVO tokenVO) {

        //因为调用logout方法之前会被JwtAuthenticationFilter拦截校验token是否合法
        //走到这里说明token合法，啥也不用做，后面只要前端收到code为800就把localstorage清空即可

        return new ResponseResult(ResponseType.LOGOUT_SUCCESS.getCode(),ResponseType.LOGOUT_SUCCESS.getMessage());
    }


}
