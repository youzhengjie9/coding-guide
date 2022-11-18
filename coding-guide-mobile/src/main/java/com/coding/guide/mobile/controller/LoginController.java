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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 移动端登录控制器
 *
 * @author youzhengjie
 * @date 2022/11/16 23:23:59
 */
@RestController
@RequestMapping(path = "/mobile/login")
@Api("移动端登录控制器")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 移动端登录接口
     *
     * @param userLoginDto 用户登录dto
     * @return {@link ResponseResult}<{@link TokenVO}>
     * @throws Throwable throwable
     */
    @ApiOperation("移动端登录接口")
    @PostMapping("/")
    public ResponseResult<TokenVO> login(@RequestBody @Valid UserLoginDTO userLoginDto, HttpServletRequest request) throws Throwable {

        return loginService.login(userLoginDto,request);
    }

}