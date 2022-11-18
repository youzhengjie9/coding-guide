package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.UserRegisterDTO;
import com.coding.guide.mobile.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 移动端注册用户控制器
 *
 * @author youzhengjie
 * @date 2022/11/18 00:25:00
 */
@RestController
@Api("移动端注册用户控制器")
@RequestMapping(path = "/mobile/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 注册
     *
     * @param userRegisterDTO 用户注册dto
     * @return {@link ResponseResult}
     */
    @PostMapping(path = "/")
    @ApiOperation("注册用户接口")
    public ResponseResult register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){

        return registerService.register(userRegisterDTO);
    }

    /**
     * 发送手机验证码
     *
     * @param phone 电话
     * @return {@link ResponseResult}<{@link String}>
     */
    @GetMapping(path = "/sendCode")
    @ApiOperation("发送手机验证码")
    public ResponseResult<String> sendCode(@RequestParam("phone") String phone){

        return registerService.sendCode(phone);
    }


}
