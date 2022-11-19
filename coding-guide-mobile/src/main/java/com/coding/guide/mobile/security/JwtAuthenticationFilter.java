package com.coding.guide.mobile.security;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.coding.guide.common.config.JwtProperties;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.exception.NotLoginException;
import com.coding.guide.common.exception.ParseTokenException;
import com.coding.guide.common.exception.TokenExpiredException;
import com.coding.guide.common.utils.JwtUtil;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * jwt身份验证过滤器
 *
 * @author youzhengjie
 * @date 2022/11/19 18:26:12
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = request.getHeader(jwtProperties.getAccessTokenName());

        //由于所有请求都要经过这个过滤器，所以分为两种情况
        //1：无accessToken的情况下，直接放行即可。（因为没有token的情况下可能是/login接口的请求此时直接放行即可,然后这个请求会经过UsernamePasswordAuthenticationToken之类过滤器）
        if(!StringUtils.hasText(accessToken)){
            filterChain.doFilter(request,response);
        }
        //2:如果有accessToken的情况下
        else {
            //解析accessToken，如果accessToken解析失败则会报错
            Claims claims = JwtUtil.parseAccessToken(accessToken);
            String userid = claims.getSubject();

            //---走到这里说明token解析成功，那么就说明用户已登录，后面只需要把SecurityUser对象设置进SpringSecurity中即可

            SecurityUser securityUser = null;
            //获取SecurityUser对象,因为后面要传给SpringSecuriry（这里使用Redis的原因是减轻MySQL的压力，防止一直查询MySQL）
            //每一个请求都会经过这里，如果此时Redis中没有该用户的SecurityUser对象，则会从数据库查询然后放到Redis中
            if(!redisTemplate.hasKey(RedisConstant.SECURITY_USER_KEY_PREFIX+userid)){
                //根据用户名查询user
                User user = userService.lambdaQuery().eq(User::getId, Long.valueOf(userid)).one();
                //构造出SecurityUser对象
                securityUser = new SecurityUser(user);
                redisTemplate.opsForValue().set(RedisConstant.SECURITY_USER_KEY_PREFIX+userid,securityUser,3, TimeUnit.DAYS);
            }else { //如果Redis中有该用户的SecurityUser对象
                securityUser= (SecurityUser) redisTemplate.opsForValue()
                        .get(RedisConstant.SECURITY_USER_KEY_PREFIX+userid);
            }

            //由于每一次新的请求SecurityContextHolder.getContext()的Authentication都会被自动清空（也就是变成null）。
            //UsernamePasswordAuthenticationToken两个参数的构造方法就是用来分别传递帐号密码的。
            //UsernamePasswordAuthenticationToken三个参数的构造方法才是用来证明用户已经登录。（这里我们一定要使用这个）
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(securityUser,null,null);

            //这里会把SecurityUser对象封装成Authentication放进SecurityContextHolder中，告诉spring security我们已经成功登录了。
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            //最后要放行
            filterChain.doFilter(request, response);
        }

    }
}
