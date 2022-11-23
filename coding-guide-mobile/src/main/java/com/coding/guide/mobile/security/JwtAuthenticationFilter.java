package com.coding.guide.mobile.security;

import com.alibaba.fastjson2.JSON;
import com.coding.guide.common.config.JwtProperties;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.JwtUtil;
import com.coding.guide.common.utils.WebUtil;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
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
import java.util.concurrent.TimeUnit;
/**
 * JWT认证过滤器
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

    /**
     * >>>>>特别注意: 所有请求都要经过这个过滤器（不管该请求路径是否在SecurityConfig类的authorizeRequests()中配置了permitAll还是authenticated都会进来这里）<<<<<<<<
     * >>>>>虽然所有请求路径（不管是permitAll还是authenticated都会进来这里），但是还是有很大不同的>>>>>>>>>.
     *
     * > 1:如果该请求路径配置在authorizeRequests方法的authenticated中，则说明需要认证才能访问。
     * > 1.1: 如果此时没有携带token的话，则进入了我们自定义的JWT过滤器就会直接放行（因为token为空我们就直接放行），
     * 此时SpringSecurity底层自带的拦截器就会判断这个请求是否配置在authenticated中，
     * 如果发现这个请求配置在authenticated中（说明需要登录才能访问），它就会在SecurityContextHolder.getContext()判断用户是否登录了，
     * 判断结果显然是用户没有登录，因为我们要想让SpringSecurity知道用户是否登录需要通过我们自定义的JWT认证过滤器中的
     * SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken的3个参数的构造方法)来设置，
     * (注意，一定要用UsernamePasswordAuthenticationToken的3个参数构造方法，只有这个方法的底层调用了super.setAuthenticated(true)，才算登录。)
     * ,但是问题就是由于这个请求没有携带token直接被放行了，
     * 导致没有执行到SecurityContextHolder.getContext().setAuthentication这行代码（因为在自定义jwt过滤器中的逻辑是：首先要有token,其次token要合法,最后才可以执行到这行代码，进行登录）
     * 所以spring security就判断用户没有登录，从而拦截该请求，并自动调用自定义未登录处理器（AuthenticationEntryPointImpl）。
     *
     * > 2: 如果该请求路径配置在authorizeRequests方法的permitAll中，则说明不需要登录也可以访问。
     * > 2.1: 此时如果没有携带token,请求也会进入JWT认证过滤器，但是进入JWT认证过滤器后会被直接放行，后面会被SpringSecurity底层拦截器拦截
     * ，当SpringSecurity发现该请求路径在permitAll中（说明不用登录也可以访问），则直接放行，允许访问该请求。
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain 过滤器链
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
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
            String userid= null;
            //解析accessToken，如果accessToken解析失败则会报错
            try {
                Claims claims = JwtUtil.parseAccessToken(accessToken);
                userid = claims.getSubject();
            }catch (Exception ex){
                ResponseResult<Object> responseResult = new ResponseResult<>();
                //Jwt不正确会走这里（解析token失败）
                if(ex instanceof MalformedJwtException){
                    responseResult.setCode(ResponseType.TOKEN_ERROR.getCode());
                    responseResult.setMsg(ResponseType.TOKEN_ERROR.getMessage());
                }
                //Jwt过期会走这里
                else if(ex instanceof ExpiredJwtException){
                    responseResult.setCode(ResponseType.ACCESS_TOKEN_EXPIRED.getCode());
                    responseResult.setMsg(ResponseType.ACCESS_TOKEN_EXPIRED.getMessage());
                }
                //返回结果给前端
                WebUtil.writeJsonString(response, JSON.toJSONString(responseResult));
                //防止代码继续往下走
                return;
            }

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
