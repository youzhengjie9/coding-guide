package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.BindEmailDTO;
import com.coding.guide.mobile.dto.UserDataDTO;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.vo.SimpleUserInfoVO;
import com.coding.guide.mobile.vo.UserCardInfoVO;
import com.coding.guide.mobile.vo.UserDataVO;

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


    /**
     * 根据回复id获取用户的id和昵称
     *
     * @return {@link User}
     */
    User getUserIdAndNickNameByReplyId(long replyId);


    /**
     * 获取当前用户的简单的用户信息（包括用户昵称、头像、积分等级）
     *
     * @return {@link SimpleUserInfoVO}
     */
    SimpleUserInfoVO getCurUserSimpleUserInfo();


    /**
     * 获取当前用户的资料（用于编辑资料数据的展示）
     *
     * @return {@link UserDataVO}
     */
    UserDataVO getCurUserData();


    /**
     * 更新用户资料
     *
     * @param userId      用户id
     * @param userDataDTO 用户资料dto
     * @return {@link ResponseResult}<{@link String}>
     */
    ResponseResult<String> updateUserData(Long userId, UserDataDTO userDataDTO);


    /**
     * 发送绑定qq邮箱验证码
     *
     * @param email qq邮箱
     * @return {@link ResponseResult}<{@link String}>
     */
    ResponseResult<String> sendBindEmailCode(String email);

    /**
     * 绑定邮箱
     *
     * @param userId       用户id
     * @param bindEmailDTO 绑定邮箱dto
     * @return {@link ResponseResult}<{@link String}>
     */
    ResponseResult<String> bindEmail(Long userId, BindEmailDTO bindEmailDTO);

}
