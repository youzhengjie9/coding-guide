package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.utils.ConverUtil;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.entity.UserFollow;
import com.coding.guide.mobile.mapper.UserFollowMapper;
import com.coding.guide.mobile.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户关注服务impl
 *
 * @author youzhengjie
 * @date 2022/11/28 09:24:10
 */
@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow> implements UserFollowService {

    private UserFollowMapper userFollowMapper;

    @Autowired
    public void setUserFollowMapper(UserFollowMapper userFollowMapper) {
        this.userFollowMapper = userFollowMapper;
    }

    @Override
    public List<Long> getFollowUserIdListByUserId(long userid) {

        return userFollowMapper.getFollowUserIdListByUserId(userid);
    }

    @Override
    public List<User> getFollowUserListByUserId(long userid) {

        return userFollowMapper.getFollowUserListByUserId(userid);
    }

    @Override
    public String getFollowCountByUserId(long userid) {

        Long followCount = userFollowMapper.getFollowCountByUserId(userid);

        return ConverUtil.converCount(followCount);
    }

    @Override
    public List<User> getFansUserListByUserId(long userid) {

        return userFollowMapper.getFansUserListByUserId(userid);
    }

    @Override
    public String getFansCountByUserId(long userid) {

        Long fansCount = userFollowMapper.getFansCountByUserId(userid);

        return ConverUtil.converCount(fansCount);
    }
}
