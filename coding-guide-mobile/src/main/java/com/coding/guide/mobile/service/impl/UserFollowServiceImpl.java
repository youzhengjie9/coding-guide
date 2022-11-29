package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.utils.ConverUtil;
import com.coding.guide.common.utils.SnowId;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.entity.UserFollow;
import com.coding.guide.mobile.mapper.UserFollowMapper;
import com.coding.guide.mobile.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    private RedisTemplate redisTemplate;

    @Autowired
    public void setUserFollowMapper(UserFollowMapper userFollowMapper) {
        this.userFollowMapper = userFollowMapper;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
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

    @Override
    public boolean isFollow(Long userid, Long followUserId) {

        return this.lambdaQuery()
                .eq(UserFollow::getUserId, userid)
                .eq(UserFollow::getFollowUserId, followUserId)
                .count() != 0;
    }

    @Override
    public boolean followUser(Long userid, Long followUserId) {

        try {
            //判断当前用户（userid）是否关注了（followUserId）这个用户
            var isFollow = this.isFollow(userid, followUserId);
            //如果关注了这个用户，则取消关注
            if(isFollow){
                //直接删除t_user_follow中的指定关注记录即可
                LambdaQueryWrapper<UserFollow> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(UserFollow::getUserId,userid)
                                  .eq(UserFollow::getFollowUserId,followUserId);
                return this.remove(lambdaQueryWrapper);
            }
            //如果没有关注这个用户，则关注
            else {
                //往t_user_follow中添加关注记录即可
                UserFollow userFollow = UserFollow.builder()
                        .id(SnowId.nextId())
                        .userId(userid)
                        .followUserId(followUserId)
                        .followTime(LocalDate.now())
                        .build();
                return this.save(userFollow);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
