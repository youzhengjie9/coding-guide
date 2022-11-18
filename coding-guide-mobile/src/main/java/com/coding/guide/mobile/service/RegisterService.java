package com.coding.guide.mobile.service;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.UserRegisterDTO;

/**
 * 注册服务
 *
 * @author youzhengjie
 * @date 2022/11/18 00:32:25
 */
public interface RegisterService {

    /**
     * 注册
     *
     * @param userRegisterDTO 用户注册dto
     * @return {@link ResponseResult}
     */
    ResponseResult register(UserRegisterDTO userRegisterDTO);


    ResponseResult<String> sendCode(String phone);
}
