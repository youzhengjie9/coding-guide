package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.vo.SimpleUserInfoVO;
import com.coding.guide.mobile.vo.UserCardInfoVO;

/**
 * 用户服务
 *
 * @author youzhengjie
 * @date 2022/11/17 23:58:54
 */
public interface UserService extends IService<User> {


    /**
     * 获取当前用户资料卡信息。
     *
     * @return {@link UserCardInfoVO}
     */
    UserCardInfoVO getCurUserCardInfo();


    /**
     * 根据用户id获取用户资料卡信息
     *
     * @param userid 用户标识
     * @return {@link UserCardInfoVO}
     */
    UserCardInfoVO getUserCardInfoByUserId(long userid);

    /**
     * 根据发布者用户id获取简单的用户信息（包括用户昵称、头像、积分等级、是否被当前用户关注）
     *
     * @param publisherId 发布者用户id
     * @return {@link SimpleUserInfoVO}
     */
    SimpleUserInfoVO getSimpleUserInfoByPublisherId(long publisherId);
}
