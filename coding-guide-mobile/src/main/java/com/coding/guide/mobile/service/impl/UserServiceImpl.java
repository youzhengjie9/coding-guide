package com.coding.guide.mobile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.utils.ConverUtil;
import com.coding.guide.mobile.entity.IntegralLevel;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.entity.UserDetail;
import com.coding.guide.mobile.mapper.UserMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.*;
import com.coding.guide.mobile.vo.SimpleUserInfoVO;
import com.coding.guide.mobile.vo.UserCardInfoVO;
import com.coding.guide.mobile.vo.UserDataVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 用户服务impl
 *
 * @author youzhengjie
 * @date 2022/11/18 00:00:09
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private QuestionService questionService;

    private UserDetailService userDetailService;

    private IntegralLevelService integralLevelService;

    private UserFollowService userFollowService;

    private UserMapper userMapper;

    private static final String DEFAULT_INTRO = "该用户暂时没有个人简介...";

    @Autowired
    public void setUserFollowService(UserFollowService userFollowService) {
        this.userFollowService = userFollowService;
    }
    @Autowired
    public void setIntegralLevelService(IntegralLevelService integralLevelService) {
        this.integralLevelService = integralLevelService;
    }
    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserCardInfoVO getCurUserCardInfo() {
        //因为每次进入这个方法之前都要经过jwt认证过滤器并每次都会从Redis拿到最新的SecurityUser对象，所以可以直接通过下面的方法拿到最新的User对象
        User user = SecurityContext.getCurrentUser();
        Long userId = user.getId();
        //执行一次bean拷贝
        UserCardInfoVO userCardInfoVO = BeanUtil.copyProperties(user, UserCardInfoVO.class);
        //查询UserDetail对象
        UserDetail userDetail = userDetailService.lambdaQuery()
                .select(
                        UserDetail::getCounty,
                        UserDetail::getProvince,
                        UserDetail::getCity,
                        UserDetail::getSchool,
                        UserDetail::getBirthday,
                        UserDetail::getIntro
                )
                .eq(UserDetail::getUserId, userId)
                .one();
        //通过birthday计算出用户年龄
        int age = userDetail.getBirthday().until(LocalDate.now()).getDays();
        userCardInfoVO.setAge(age);
        //address
        String country=userDetail.getCounty();
        String provice=userDetail.getProvince();
        String city=userDetail.getCity();
        String address = ConverUtil.converAddress(country, provice, city);
        userCardInfoVO.setAddress(address);
        //school
        userCardInfoVO.setSchool(Objects.isNull(userDetail.getSchool())? "" : userDetail.getSchool());
        //intro
        userCardInfoVO.setIntro(StringUtils.isBlank(userDetail.getIntro()) ? DEFAULT_INTRO : userCardInfoVO.getIntro());
        //查询点赞总数
        String likedCount = questionService.selectLikedCountByUserId(userId);
        //查询收藏总数
        String collectedCount = questionService.selectCollectedCountByUserId(userId);
        //查询关注数
        String followCount = userFollowService.getFollowCountByUserId(userId);
        //查询粉丝数
        String fansCount = userFollowService.getFansCountByUserId(userId);
        //放入userCardInfoVO对象中
        userCardInfoVO.setLikedCount(likedCount)
                .setCollectedCount(collectedCount)
                .setFollowCount(followCount)
                .setFansCount(fansCount);
        return userCardInfoVO;
    }

    @Override
    public UserCardInfoVO getUserCardInfoByUserId(long userid) {

        //查询User对象
        User user = this.lambdaQuery()
                .select(
                        User::getUserName,
                        User::getNickName,
                        User::getSex,
                        User::getAvatar
                )
                .eq(User::getId, userid)
                .eq(User::getStatus, 0)
                .one();
        //只有当User对象查询不为空才执行下面操作
        //user对象不为空说明这个用户是存在的、并且status为0和del_flag=0。
        if(Objects.nonNull(user)){
            //执行一次bean拷贝
            UserCardInfoVO userCardInfoVO = BeanUtil.copyProperties(user, UserCardInfoVO.class);
            //查询UserDetail对象
            UserDetail userDetail = userDetailService.lambdaQuery()
                    .select(
                            UserDetail::getCounty,
                            UserDetail::getProvince,
                            UserDetail::getCity,
                            UserDetail::getSchool,
                            UserDetail::getBirthday,
                            UserDetail::getIntro
                    )
                    .eq(UserDetail::getUserId, userid)
                    .one();
            //通过birthday计算出用户年龄
            int age = userDetail.getBirthday().until(LocalDate.now()).getDays();
            userCardInfoVO.setAge(age);
            //address
            String country=userDetail.getCounty();
            String provice=userDetail.getProvince();
            String city=userDetail.getCity();
            String address = ConverUtil.converAddress(country, provice, city);
            userCardInfoVO.setAddress(address);
            //school
            userCardInfoVO.setSchool(Objects.isNull(userDetail.getSchool())? "" : userDetail.getSchool());
            //intro
            userCardInfoVO.setIntro(StringUtils.isBlank(userDetail.getIntro()) ? DEFAULT_INTRO : userCardInfoVO.getIntro());
            //查询点赞总数
            String likedCount = questionService.selectLikedCountByUserId(userid);
            //查询收藏总数
            String collectedCount = questionService.selectCollectedCountByUserId(userid);
            //查询关注数
            String followCount = userFollowService.getFollowCountByUserId(userid);
            //查询粉丝数
            String fansCount = userFollowService.getFansCountByUserId(userid);
            //放入userCardInfoVO对象中
            userCardInfoVO.setLikedCount(likedCount)
                    .setCollectedCount(collectedCount)
                    .setFollowCount(followCount)
                    .setFansCount(fansCount);
            return userCardInfoVO;
        }
        //反之如果user对象为空则返回null
        return null;
    }

    @Override
    public SimpleUserInfoVO getSimpleUserInfoByPublisherId(long publisherId) {

        //获取当前登录的用户id。
        var currentUserId = SecurityContext.getCurrentUserId();
        //根据发布者用户id（publisherId）查询用户的nickName和avatar和integral
        var user = this.lambdaQuery()
                .select(
                        User::getNickName,
                        User::getAvatar,
                        User::getIntegral
                )
                .eq(User::getId, publisherId)
                .eq(User::getStatus, 0)
                .one();
        //第一次bean拷贝
        var simpleUserInfoVO = BeanUtil.copyProperties(user, SimpleUserInfoVO.class);
        //根据publisherId查询其学校
        UserDetail userDetail = userDetailService.lambdaQuery()
                .select(UserDetail::getSchool)
                .eq(UserDetail::getUserId, publisherId)
                .one();
        //获取用户当前的积分
        var integral = user.getIntegral();
        var integralLevel = integralLevelService.getIntegralLevelByIntegral(integral);
        String levelFormat = "Lv" + integralLevel.getLevel() + "-" + integralLevel.getDescribe();
        simpleUserInfoVO.setBackgroundColor(integralLevel.getBackgroundColor())
                        .setLevelFormat(levelFormat)
                        .setPublisherId(publisherId)
                        .setSchool((Objects.nonNull(userDetail)) ? userDetail.getSchool() : "" );

        // TODO: 2022/11/28 查询当前用户（currentUserId）是否关注了该用户（publisherId）

        return simpleUserInfoVO;
    }

    @Override
    public User getUserIdAndNickNameByReplyId(long replyId) {


        return userMapper.getUserIdAndNickNameByReplyId(replyId);
    }

    @Override
    public SimpleUserInfoVO getCurUserSimpleUserInfo() {

        //获取当前登录的用户id。
        var currentUserId = SecurityContext.getCurrentUserId();
        //根据当前用户的id查询用户的nickName和avatar和integral
        var user = this.lambdaQuery()
                .select(
                        User::getNickName,
                        User::getAvatar,
                        User::getIntegral
                )
                .eq(User::getId, currentUserId)
                .eq(User::getStatus, 0)
                .one();
        //第一次bean拷贝
        var simpleUserInfoVO = BeanUtil.copyProperties(user, SimpleUserInfoVO.class);
        //根据currentUserId查询其学校
        UserDetail userDetail = userDetailService.lambdaQuery()
                .select(UserDetail::getSchool)
                .eq(UserDetail::getUserId, currentUserId)
                .one();
        //获取用户当前的积分
        var integral = user.getIntegral();
        var integralLevel = integralLevelService.getIntegralLevelByIntegral(integral);
        String levelFormat = "Lv" + integralLevel.getLevel() + "-" + integralLevel.getDescribe();
        simpleUserInfoVO.setBackgroundColor(integralLevel.getBackgroundColor())
                .setLevelFormat(levelFormat)
                .setPublisherId(currentUserId)
                .setSchool((Objects.nonNull(userDetail)) ? userDetail.getSchool() : "" );

        return simpleUserInfoVO;

    }

    @Override
    public UserDataVO getCurUserData() {

        Long currentUserId = SecurityContext.getCurrentUserId();

        User user = this.lambdaQuery().select(
                User::getNickName,
                User::getAvatar,
                User::getSex
        ).eq(User::getId, currentUserId).one();

        UserDetail userDetail = userDetailService.lambdaQuery().select(
                UserDetail::getIntro,
                UserDetail::getBirthday,
                UserDetail::getCounty,
                UserDetail::getProvince,
                UserDetail::getCity,
                UserDetail::getSchool,
                UserDetail::getEmail,
                UserDetail::getPhone
        ).eq(UserDetail::getUserId, currentUserId).one();


        UserDataVO userDataVO = BeanUtil.copyProperties(user, UserDataVO.class);

        BeanUtil.copyProperties(userDetail,userDataVO);

        String county = userDetail.getCounty();
        String province = userDetail.getProvince();
        String city = userDetail.getCity();

        String address = ConverUtil.converAddress(county, province, city);
        userDataVO.setAddress(address);

        return userDataVO;

    }


}

