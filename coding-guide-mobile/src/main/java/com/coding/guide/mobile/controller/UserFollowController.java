package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.UserFollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 用户关注控制器
 *
 * @author youzhengjie
 * @date 2022/11/28 15:17:49
 */
@RestController
@RequestMapping(path = "/mobile/user/follow")
@Api("用户关注控制器")
public class UserFollowController {

    private UserFollowService userFollowService;

    @Autowired
    public void setUserFollowService(UserFollowService userFollowService) {
        this.userFollowService = userFollowService;
    }

    /**
     * 查询当前用户关注的所有用户的id集合
     *
     * @return {@link ResponseResult}<{@link List}<{@link Long}>>
     */
    @GetMapping(path = "/selectCurUserFollowUserIdList")
    public ResponseResult<List<Long>> selectCurUserFollowUserIdList(){

        var currentUserId = SecurityContext.getCurrentUserId();

        var followUserIdList = userFollowService.getFollowUserIdListByUserId(currentUserId);

        return ResponseResult.ok(followUserIdList);
    }

    /**
     * 关注用户
     *
     * @param followUserId 被关注的用户id
     * @return {@link ResponseResult}<{@link Object}>
     */
    @GetMapping(path = "/{followUserId}")
    @ApiOperation("关注用户")
    public ResponseResult<Object> followUser(@PathVariable("followUserId") Long followUserId){
        try {
            //当前的用户id
            var userid = SecurityContext.getCurrentUserId();
            var res= userFollowService.followUser(userid,followUserId);
            return ResponseResult.ok(res);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }
    }


}
