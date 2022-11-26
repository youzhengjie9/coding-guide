package com.coding.guide.mobile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.mapper.UserMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.QuestionService;
import com.coding.guide.mobile.service.UserService;
import com.coding.guide.mobile.vo.UserCardInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务impl
 *
 * @author youzhengjie
 * @date 2022/11/18 00:00:09
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private QuestionService questionService;

    @Override
    public UserCardInfoVO getCurUserCardInfo() {
        //因为每次进入这个方法之前都要经过jwt认证过滤器并每次都会从Redis拿到最新的SecurityUser对象，所以可以直接通过下面的方法拿到最新的User对象
        User user = SecurityContext.getCurrentUser();
        UserCardInfoVO userCardInfoVO = BeanUtil.copyProperties(user, UserCardInfoVO.class);
        String likedCount = questionService.selectLikedCountByUserId(user.getId());
        String collectedCount = questionService.selectCollectedCountByUserId(user.getId());
        userCardInfoVO.setLikedCount(likedCount)
                      .setCollectedCount(collectedCount);


        return userCardInfoVO;
    }

    @Override
    public UserCardInfoVO getUserCardInfoByUserId(long userid) {

        User user = this.lambdaQuery()
                .select(
                        User::getUserName,
                        User::getNickName,
                        User::getEmail,
                        User::getPhone,
                        User::getSex,
                        User::getAvatar
                )
                .eq(User::getId, userid)
                .eq(User::getStatus, 0).one();
        UserCardInfoVO userCardInfoVO = BeanUtil.copyProperties(user, UserCardInfoVO.class);
        String likedCount = questionService.selectLikedCountByUserId(userid);
        String collectedCount = questionService.selectCollectedCountByUserId(userid);
        userCardInfoVO.setLikedCount(likedCount)
                      .setCollectedCount(collectedCount);

        return userCardInfoVO;
    }
}

