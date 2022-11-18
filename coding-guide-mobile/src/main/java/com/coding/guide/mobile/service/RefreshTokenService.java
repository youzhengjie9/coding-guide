package com.coding.guide.mobile.service;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.vo.TokenVO;

/**
 * 刷新令牌服务
 *
 * @author youzhengjie
 * @date 2022/11/19 01:24:07
 */
public interface RefreshTokenService {

    ResponseResult<TokenVO> refresh(String refreshToken);

}
