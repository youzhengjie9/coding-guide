package com.coding.guide.mobile.security;


import com.alibaba.fastjson2.JSON;
import com.coding.guide.common.config.JwtProperties;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.exception.NotLoginException;
import com.coding.guide.common.exception.ParseTokenException;
import com.coding.guide.common.exception.TokenExpiredException;
import com.coding.guide.common.utils.JwtUtil;
import com.coding.guide.mobile.constant.RedisConstant;
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

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = request.getHeader(jwtProperties.getAccessTokenName());

        //由于所有请求都要经过这个过滤器，所以分为两种情况
        //1：无accessToken的情况下，直接放行即可。（因为没有token的情况下可能是/user/login接口的请求此时直接放行即可,然后这个请求会经过UsernamePasswordAuthenticationToken之类过滤器）
        if(!StringUtils.hasText(accessToken)){

            filterChain.doFilter(request,response);

        }
        //2:如果有accessToken的情况下
        else {

            String userid=null;
            //先解析accessToken，如果accessToken解析失败则直接报错即可。
            ResponseResult responseResult;
            try {
                Claims claims = JwtUtil.parseAccessToken(accessToken);
                userid = claims.getSubject();
            }catch (ExpiredJwtException expiredJwtException){
                responseResult = new ResponseResult<>();
                responseResult.setCode(ResponseType.ACCESS_TOKEN_EXPIRED.getCode());
                responseResult.setMsg(ResponseType.ACCESS_TOKEN_EXPIRED.getMessage());

                String jsonString = JSON.toJSONString(responseResult);
                //将报错信息传来AuthenticationEntryPointImpl类，然后由它进行return到前端
                request.setAttribute("responseResult",jsonString);

                throw new TokenExpiredException();
            }catch (Exception e){
                responseResult = new ResponseResult<>();
                responseResult.setCode(ResponseType.TOKEN_ERROR.getCode());
                responseResult.setMsg(ResponseType.TOKEN_ERROR.getMessage());

                String jsonString = JSON.toJSONString(responseResult);
                //将报错信息传来AuthenticationEntryPointImpl类，然后由它进行return到前端
                request.setAttribute("responseResult",jsonString);

                throw new ParseTokenException();
            }

            //通过userid去redis中查询（拿到的就是loginUser对象），如果没有记录则直接报错（说明未登录）
            //只有redis中存在该用户的loginUser对象才能证明用户已经登录，否则说明用户未登录
            String key= RedisConstant.LOGIN_KEY_PREFIX +userid;
            LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get(key);
            if(Objects.isNull(loginUser)){
                responseResult = new ResponseResult<>();
                responseResult.setCode(ResponseType.NOT_LOGIN.getCode());
                responseResult.setMsg(ResponseType.NOT_LOGIN.getMessage());

                String jsonString = JSON.toJSONString(responseResult);
                //将报错信息传来AuthenticationEntryPointImpl类，然后由它进行return到前端
                request.setAttribute("responseResult",jsonString);
                throw new NotLoginException();
            }


            //由于每一次新的请求SecurityContextHolder.getContext()的Authentication都会被自动清空（也就是变成null）。
            //为了解决这个问题，我们要在JwtAuthenticationFilter中判断用户是否登录（判断redis中LOGIN_KEY_PREFIX+userid的key）
            //如果登录了就要重新设置Authentication,把loginUser和权限放进去,确保SpringSecurity知道用户已经登录
            //UsernamePasswordAuthenticationToken两个参数的构造方法就是用来分别传递帐号密码的。
            //UsernamePasswordAuthenticationToken三个参数的构造方法才是用来证明用户已经登录。（这里我们一定要使用这个）
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());

            //这里会把loginUser封装成Authentication放进SecurityContextHolder中，告诉spring security我们已经成功登录了。
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            //最后要放行
            filterChain.doFilter(request, response);

        }

    }
}
