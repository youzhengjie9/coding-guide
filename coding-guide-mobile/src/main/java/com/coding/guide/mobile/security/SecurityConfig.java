package com.coding.guide.mobile.security;

import com.coding.guide.mobile.security.handle.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 新版SpringSecurity配置类
 * Spring Boot 2.7.0之后WebSecurityConfigurerAdapter就过时了
 * spring security过滤器执行顺序：
 * 第一：JwtAuthenticationFilter（jwt认证用的）
 * 第二：UsernamePasswordAuthenticationFilter（检查帐号密码是否正切）
 * 第三：ExceptionTranslationFilter（如果不正确就会在这个过滤器抛出异常）
 * 第四：FilterSecurityInterceptor （如果上面的过滤器顺利执行，没有报错，则说明成功了）
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启SpringSecurity注解鉴权模式
public class SecurityConfig {

    /**
     * 下面这些请求《不用登录》（也就是不用携带Token）都可以访问
     */
    private final String[] PERMIT_PATHS={
            "/mobile/login/",
            "/mobile/register/**"
    };

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //解决前端vue无法通过iframe嵌套druid这些外部监控页面问题
                .headers().frameOptions().disable()
                .and()
                //关闭csrf
                .csrf().disable()
                //设置允许跨域
                .cors()
                .and()
                //关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //配置认证请求
                .authorizeRequests()
                // 设置部分请求《不用登录》（也就是不用携带Token）都可以访问
                .antMatchers(PERMIT_PATHS).permitAll()
                // 除了上面的请求之外，其他所有请求《只有登录了》（要携带token）才能访问，否则会被SpringSecurity底层拦截器拦截
                .anyRequest().authenticated()
                .and()
                //添加jwt认证过滤器，并放在UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //配置异常处理器（只有抛出异常才会到这里的处理器）
                .exceptionHandling()
                //配置失败处理器（权限不足调用）
                .accessDeniedHandler(accessDeniedHandler)
                //配置失败处理器（登录认证失败后调用）
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                //结束标志。构建SecurityFilterChain并返回
                .build();
    }

    /**
     * AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{

        return authenticationConfiguration.getAuthenticationManager();
    }


}
