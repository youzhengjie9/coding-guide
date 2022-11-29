package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.service.RefreshTokenService;
import com.coding.guide.mobile.vo.TokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 刷新令牌控制器
 *
 * @author youzhengjie
 * @date 2022/11/19 01:23:48
 */
@RestController
@Api("刷新token接口")
@RequestMapping(path = "/mobile")
public class RefreshTokenController {

    private RefreshTokenService refreshTokenService;

    @Autowired
    public void setRefreshTokenService(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    /**
     * RequestHeader注解可以接收前端传来的名为value（refreshToken）的请求头，
     * 并自动把接收到的refreshToken请求头存入String refreshToken
     * @param refreshToken 请求头中名为（refreshToken）的内容
     * @return ResponseResult<TokenVO>
     */

//    @OperationLog("刷新token") //refreshToken方法上面不能加上 @OperationLog注解，否则刷新功能会失效
    @PostMapping("/refreshToken")
    @ApiOperation("刷新token")
    public ResponseResult<TokenVO> refreshToken(@RequestHeader(value = "refreshToken") String refreshToken){

        return refreshTokenService.refresh(refreshToken);
    }

}
