package com.coding.guide.mobile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.utils.ConverUtil;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.entity.UserDetail;
import com.coding.guide.mobile.mapper.UserMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.QuestionService;
import com.coding.guide.mobile.service.UserDetailService;
import com.coding.guide.mobile.service.UserService;
import com.coding.guide.mobile.vo.UserCardInfoVO;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserDetailService userDetailService;

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
                        UserDetail::getBirthday
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
        //查询点赞总数
        String likedCount = questionService.selectLikedCountByUserId(userId);
        //查询收藏总数
        String collectedCount = questionService.selectCollectedCountByUserId(userId);
        //设置点赞总数和收藏总数到userCardInfoVO对象
        userCardInfoVO.setLikedCount(likedCount)
                .setCollectedCount(collectedCount);
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
                            UserDetail::getBirthday
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
            //查询点赞总数
            String likedCount = questionService.selectLikedCountByUserId(userid);
            //查询收藏总数
            String collectedCount = questionService.selectCollectedCountByUserId(userid);
            //设置点赞总数和收藏总数到userCardInfoVO对象
            userCardInfoVO.setLikedCount(likedCount)
                    .setCollectedCount(collectedCount);
            return userCardInfoVO;
        }
        //反之如果user对象为空则返回null
        return null;
    }
}

