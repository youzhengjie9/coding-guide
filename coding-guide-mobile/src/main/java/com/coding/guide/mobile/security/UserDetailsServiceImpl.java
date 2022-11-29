package com.coding.guide.mobile.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Objects;

/**
 * 用户详细信息服务impl
 *
 * @author youzhengjie
 * @date 2022/11/17 13:37:14
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询user
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));

        if(Objects.isNull(user)){
            //直接抛出异常即可，然后会自动调用AuthenticationEntryPointImpl中的方法
            throw new RuntimeException();
        }

        //封装返回loginUser对象
        return new SecurityUser(user);
    }
}
