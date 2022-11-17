package com.coding.guide.mobile.service;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.UserLoginDTO;
import com.coding.guide.mobile.vo.TokenVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录服务
 *
 * @author youzhengjie
 * @date 2022/11/16 23:24:10
 */
public interface LoginService {


    ResponseResult<TokenVO> login(UserLoginDTO userLoginDto, HttpServletRequest request) throws Throwable;



}
