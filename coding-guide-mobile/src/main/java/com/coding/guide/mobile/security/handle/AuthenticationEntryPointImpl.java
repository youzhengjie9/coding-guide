package com.coding.guide.mobile.security.handle;

import com.alibaba.fastjson2.JSON;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.WebUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败处理器
 *
 * @author youzhengjie
 * @date 2022/11/21 20:00:21
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {


    /**
     * authException的几种类型：
     * 1：如果用户名或密码不正确，则authException为org.springframework.security.authentication.BadCredentialsException或
     * authException为org.springframework.security.authentication.InternalAuthenticationServiceException两者其中一个
     * 2：如果用户未进行登录、又访问了SecurityConfig配置类中的antMatchers("拦截路径列表").authenticated()中定义的拦截路径
     * 则会抛出authException为org.springframework.security.authentication.InsufficientAuthenticationException异常
     *
     * @param request       请求
     * @param response      响应
     * @param authException 身份验证异常
     * @throws IOException      ioexception
     * @throws ServletException servlet异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseResult<Object> responseResult = new ResponseResult<>();
        //第一种情况：如果用户名或密码不正确，则会进来这里
        if(authException instanceof BadCredentialsException ||
                authException instanceof InternalAuthenticationServiceException){
            responseResult.setCode(ResponseType.USERNAME_PASSWORD_ERROR.getCode());
            responseResult.setMsg(ResponseType.USERNAME_PASSWORD_ERROR.getMessage());
        }
        //第二种情况：如果用户未进行登录、又访问了SecurityConfig配置类中的antMatchers("拦截路径列表").authenticated()中定义的拦截路径
        else if(authException instanceof InsufficientAuthenticationException){
            responseResult.setCode(ResponseType.NOT_LOGIN.getCode());
            responseResult.setMsg(ResponseType.NOT_LOGIN.getMessage());
        }
        //返回给前端
        WebUtil.writeJsonString(response, JSON.toJSONString(responseResult));
    }
}
