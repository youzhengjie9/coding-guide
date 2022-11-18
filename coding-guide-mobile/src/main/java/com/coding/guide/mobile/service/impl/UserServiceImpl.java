package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.mapper.UserMapper;
import com.coding.guide.mobile.service.UserService;
import lombok.extern.slf4j.Slf4j;
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



}

