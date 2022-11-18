package com.coding.guide.mobile.security;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.exception.UserNameOrPasswordException;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询user
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));

        //-------只有帐号不存在才会走这里，如果帐号存在，但是密码不正确则不会走这里-------
        if(Objects.isNull(user)){
            ResponseResult responseResult = new ResponseResult<>();
            responseResult.setCode(ResponseType.USERNAME_PASSWORD_ERROR.getCode());
            responseResult.setMsg(ResponseType.USERNAME_PASSWORD_ERROR.getMessage());

            String jsonString = JSON.toJSONString(responseResult);
            //将报错信息传来AuthenticationEntryPointImpl类，然后由它进行return到前端
            request.setAttribute("responseResult",jsonString);
            throw new UserNameOrPasswordException();
        }

        //封装返回loginUser对象
        return new LoginUser(user);
    }
}
