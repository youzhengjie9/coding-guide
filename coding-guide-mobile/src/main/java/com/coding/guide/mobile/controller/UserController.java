package com.coding.guide.mobile.controller;

import cn.hutool.core.bean.BeanUtil;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.BindEmailDTO;
import com.coding.guide.mobile.dto.UserDataDTO;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.security.SecurityUser;
import com.coding.guide.mobile.service.UserService;
import com.coding.guide.mobile.vo.SimpleUserInfoVO;
import com.coding.guide.mobile.vo.TokenVO;
import com.coding.guide.mobile.vo.UserCardInfoVO;
import com.coding.guide.mobile.vo.UserDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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

    /**
     * 获取当前用户资料卡信息。
     *
     * @return {@link ResponseResult}
     */
    @GetMapping(path = "/getCurUserCardInfo")
    @ApiModelProperty("获取用户资料卡信息")
    public ResponseResult<UserCardInfoVO> getCurUserCardInfo(){

        UserCardInfoVO userCardInfoVO =userService.getCurUserCardInfo();

        return ResponseResult.ok(userCardInfoVO);
    }

    /**
     * 根据用户id获取用户资料卡信息
     *
     * @return {@link ResponseResult}
     */
    @GetMapping(path = "/getUserCardInfoByUserId")
    @ApiModelProperty("根据用户id获取用户资料卡信息")
    public ResponseResult<UserCardInfoVO> getUserCardInfoByUserId(@RequestParam("userid") long userid){

        UserCardInfoVO userCardInfoVO =userService.getUserCardInfoByUserId(userid);

        return ResponseResult.ok(userCardInfoVO);
    }

    /**
     * 根据发布者用户id获取简单的用户信息（包括用户昵称、头像、积分等级、是否被当前用户关注）
     *
     * @param publisherId 发布者用户id
     * @return {@link ResponseResult}<{@link SimpleUserInfoVO}>
     */
    @GetMapping(path = "/getSimpleUserInfoByPublisherId")
    @ApiModelProperty("根据发布者用户id获取简单的用户信息")
    public ResponseResult<SimpleUserInfoVO> getSimpleUserInfoByPublisherId(@RequestParam("publisherId") long publisherId){

        SimpleUserInfoVO simpleUserInfoVO=userService.getSimpleUserInfoByPublisherId(publisherId);

        return ResponseResult.ok(simpleUserInfoVO);
    }

    /**
     * 获取当前用户的简单的用户信息（包括用户昵称、头像、积分等级）
     *
     * @return {@link ResponseResult}<{@link SimpleUserInfoVO}>
     */
    @GetMapping(path = "/getCurUserSimpleUserInfo")
    @ApiModelProperty("获取当前用户的简单的用户信息")
    public ResponseResult<SimpleUserInfoVO> getCurUserSimpleUserInfo(){

        SimpleUserInfoVO simpleUserInfoVO=userService.getCurUserSimpleUserInfo();

        return ResponseResult.ok(simpleUserInfoVO);
    }

    /**
     * 获取当前用户的资料（用于编辑资料数据的展示）
     *
     * @return {@link ResponseResult}<{@link UserDataVO}>
     */
    @GetMapping(path = "/getCurUserData")
    public ResponseResult<UserDataVO> getCurUserData(){
        try {
            UserDataVO userDataVO=userService.getCurUserData();
            return ResponseResult.ok(userDataVO);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }
    }

    /**
     * 修改当前用户的资料
     *
     * @param userDataDTO 用户数据dto
     * @return {@link ResponseResult}<{@link String}>
     */
    @PutMapping(path = "/updateCurUserData")
    public ResponseResult<String> updateCurUserData(@RequestBody UserDataDTO userDataDTO){

        try {
            //获取当前用户id
            Long userId = SecurityContext.getCurrentUserId();
            return userService.updateUserData(userId,userDataDTO);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }

    }

    /**
     * 发送绑定qq邮箱验证码
     *
     * @param email qq邮箱
     * @return {@link ResponseResult}<{@link String}>
     */
    @GetMapping(path = "/sendBindEmailCode")
    public ResponseResult<String> sendBindEmailCode(@RequestParam("email") String email){

        try {
            return userService.sendBindEmailCode(email);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail(null);
        }

    }

    /**
     * 绑定邮箱
     * @param bindEmailDTO 绑定邮箱dto
     * @return {@link ResponseResult}<{@link String}>
     */
    @PutMapping(path = "/bindEmail")
    public ResponseResult<String> bindEmail(@RequestBody @Valid BindEmailDTO bindEmailDTO){
        try {
            //获取当前用户id
            Long userId = SecurityContext.getCurrentUserId();
            return userService.bindEmail(userId,bindEmailDTO);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }

    }

}
