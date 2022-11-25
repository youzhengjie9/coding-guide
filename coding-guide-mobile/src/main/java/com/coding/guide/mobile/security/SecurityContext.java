package com.coding.guide.mobile.security;

import com.coding.guide.mobile.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 封装SpringSecurity上下文
 *
 * @author youzhengjie
 * @date 2022/11/25 11:47:23
 */
public final class SecurityContext {

    /**
     * 禁止用new创建SecurityContext对象
     */
    private SecurityContext(){

    }

    /**
     * 得到当前SecurityUser对象
     *
     * @return {@link SecurityUser}
     */
    public static SecurityUser getCurrentSecurityUser(){
        return (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    /**
     * 获取当前User对象
     *
     * @return {@link User}
     */
    public static User getCurrentUser(){
        return SecurityContext.getCurrentSecurityUser().getUser();
    }

    /**
     * 获得当前用户id
     *
     * @return {@link Long}
     */
    public static Long getCurrentUserId(){
        return SecurityContext.getCurrentUser().getId();
    }

    /**
     * 获得当前用户名
     *
     * @return {@link String}
     */
    public static String getCurrentUserName(){
        return SecurityContext.getCurrentUser().getUserName();
    }

}
