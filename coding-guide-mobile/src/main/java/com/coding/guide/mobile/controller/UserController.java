package com.coding.guide.mobile.controller;

import cn.hutool.core.bean.BeanUtil;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.security.SecurityUser;
import com.coding.guide.mobile.vo.TokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author youzhengjie
 * @date 2022/11/17 23:10:04
 */
@RestController
@Api("用户控制器")
@RequestMapping(path = "/mobile/user")
public class UserController {

    /**
     * 获取当前用户信息
     * 记住：要携带accessToken
     * @return {@link ResponseResult}
     */
    @GetMapping("/getCurrentUserInfo")
    @ApiOperation("获取当前用户信息")
    public ResponseResult<TokenVO> getCurrentUserInfo(){

        try {
            TokenVO tokenVO = BeanUtil.copyProperties(SecurityContext.getCurrentUser(), TokenVO.class);
            return ResponseResult.ok(tokenVO);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }
    }



}
