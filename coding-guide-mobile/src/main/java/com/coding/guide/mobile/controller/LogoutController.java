package com.coding.guide.mobile.controller;


import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.service.LogoutService;
import com.coding.guide.mobile.vo.TokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 退出登录控制器
 *
 * @author youzhengjie
 * @date 2022/11/19 15:03:11
 */
@RestController
@RequestMapping(path = "/mobile/logout")
@Api("退出登录控制器")
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    /**
     * 用户退出接口。
     * @param accessToken （必须要传）
     * @return
     */
    @ApiOperation("退出登录接口")
    @PostMapping("/")
    public ResponseResult logout(@RequestHeader(value = "accessToken") String accessToken){

        TokenVO tokenVO = TokenVO.builder()
                .accessToken(accessToken)
                .build();
        return logoutService.logout(tokenVO);
    }
    

}
