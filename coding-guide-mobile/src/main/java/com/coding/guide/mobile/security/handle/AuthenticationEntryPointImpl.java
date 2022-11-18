package com.coding.guide.mobile.security.handle;

import com.coding.guide.common.utils.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义失败处理器（登录认证失败后调用）
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {


        //如果帐号存在，但是密码不正确，则request.getAttribute("responseResult")获取到的是null，并返回给前端
        //前端拿到响应结果，如果为null则说明是帐号正确、密码错误。
        String responseResultJson = (String) request.getAttribute("responseResult");

        WebUtil.writeJsonString(response,responseResultJson);
    }
}
