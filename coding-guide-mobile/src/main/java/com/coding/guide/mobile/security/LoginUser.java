package com.coding.guide.mobile.security;

import com.alibaba.fastjson2.annotation.JSONField;
import com.coding.guide.mobile.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * spring security登录用户类
 *
 * @author youzhengjie
 * @date 2022/11/16 23:55:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private User user;

    /**
     * 框架所需要的权限集合
     */
    @JSONField(serialize = false) //禁止序列化该属性
    private Set<SimpleGrantedAuthority> grantedAuthoritySet;

    public LoginUser(User user){
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return (user.getDelFlag()==0 && user.getStatus()==0);
    }
}
