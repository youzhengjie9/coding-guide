package com.coding.guide.mobile.service;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.vo.TokenVO;

/**
 * 退出登录服务
 *
 * @author youzhengjie
 * @date 2022/11/19 15:02:43
 */
public interface LogoutService {

    ResponseResult logout(TokenVO tokenVO);


}
