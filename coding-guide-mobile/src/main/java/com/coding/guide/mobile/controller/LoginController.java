package com.coding.guide.mobile.controller;

import cn.hutool.core.bean.BeanUtil;
import com.coding.guide.mobile.security.LoginUser;
import com.coding.guide.mobile.service.LoginService;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.UserLoginDTO;
import com.coding.guide.mobile.vo.TokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 登录控制器
 *
 * @author youzhengjie
 * @date 2022/11/16 23:23:59
 */
@RestController
@Api("登录接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param userLoginDto 用户登录dto
     * @return {@link ResponseResult}<{@link TokenVO}>
     * @throws Throwable throwable
     */
    @ApiOperation("登录接口")
    @PostMapping("/user/login")
    public ResponseResult<TokenVO> login(@RequestBody @Valid UserLoginDTO userLoginDto, HttpServletRequest request) throws Throwable {


        return loginService.login(userLoginDto,request);
    }

    /**
     * 获取当前用户信息
     * 记住：要携带accessToken
     * @return {@link ResponseResult}
     */
    @GetMapping("/user/getCurrentUserInfo")
    @ApiOperation("获取当前用户信息")
    public ResponseResult<TokenVO> getCurrentUserInfo(){

        try {
            LoginUser loginUser=(LoginUser) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            TokenVO tokenVO = BeanUtil.copyProperties(loginUser.getUser(), TokenVO.class);

            return ResponseResult.ok(tokenVO);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }
    }

}