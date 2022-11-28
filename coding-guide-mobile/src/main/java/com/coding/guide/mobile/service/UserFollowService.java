package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.entity.UserFollow;

import java.util.List;

/**
 * 用户关注服务
 *
 * @author youzhengjie
 * @date 2022/11/28 09:23:08
 */
public interface UserFollowService extends IService<UserFollow> {


    /**
     * 查询指定用户关注的所有用户的id集合
     *
     * @param userid 用户id
     * @return {@link List}<{@link Long}>
     */
    List<Long> getFollowUserIdListByUserId(long userid);

    /**
     * 查询指定用户关注的所有用户对象集合
     *
     * @param userid 用户id
     * @return {@link List}<{@link User}>
     */
    List<User> getFollowUserListByUserId(long userid);

    /**
     * 查询指定用户关注数（返回结果要进行数量格式化）
     *
     * @param userid 用户id
     * @return {@link String}
     */
    String getFollowCountByUserId(long userid);


    /**
     * 查询指定用户的所有粉丝用户对象集合
     *
     * @param userid 用户id
     * @return {@link List}<{@link User}>
     */
    List<User> getFansUserListByUserId(long userid);

    /**
     * 查询指定用户粉丝数（返回结果要进行数量格式化）
     *
     * @param userid 用户id
     * @return {@link String}
     */
    String getFansCountByUserId(long userid);

}
