package com.coding.guide.mobile.service.impl;

import com.alibaba.fastjson2.JSON;
import com.coding.guide.common.config.JwtProperties;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.BrowserUtils;
import com.coding.guide.common.utils.IpToAddressUtil;
import com.coding.guide.common.utils.IpUtils;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.dto.UserLoginDTO;
import com.coding.guide.mobile.security.LoginUser;
import com.coding.guide.mobile.service.LoginService;
import com.coding.guide.common.utils.JwtUtil;
import com.coding.guide.mobile.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录服务impl
 *
 * @author youzhengjie
 * @date 2022/11/16 23:35:50
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * loginUser过期时间。默认单位（毫秒）
     */
    private long loginUserExpired;

    /**
     * 初始化配置
     */
    @PostConstruct
    void initProperties(){
        loginUserExpired=jwtProperties.getRefreshTokenExpired();
    }

    @Override
    public ResponseResult<TokenVO> login(UserLoginDTO userLoginDto, HttpServletRequest request) throws Throwable {

        //这个就是我们前端表单传入的UserDto（封装了前端提交的帐号密码），目的是为了后面检查帐号密码是否正确
        //--------------------
        //UsernamePasswordAuthenticationToken两个参数的构造方法就是用来分别传递帐号密码的。（这里我们一定要使用这个）
        //UsernamePasswordAuthenticationToken三个参数的构造方法才是用来证明用户已经登录。
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(),userLoginDto.getPassword());

        //1：authenticationManager.authenticate底层就是调用了UserDetailsService的loadUserByUserName方法，获取到UserDetails对象（也就是LoginUser对象）
        //2：将usernamePasswordAuthenticationToken（前端传入的帐号密码）和loadUserByUsername中的userMapper.selectOne(lambdaQueryWrapper)方法查询的帐号密码进行比对，判断帐号密码输入是否正确。
        //2.1：如果是帐号不存在的话，就会在loadUserByUsername方法中抛出异常并且被AuthenticationEntryPointImpl方法捕获，返回（code：601）
        //2.2：如果是帐号存在，但是密码不正确的话，就会在AuthenticationEntryPointImpl方法返回null给前端
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //------走到这一步，证明帐号密码都是正确的，可以给前端返回jwt token了
        // 本质上authenticate.getPrincipal()拿到的就是LoginUser对象
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userid = loginUser.getUser().getId();

        //根据userid生成accessToken和refreshToken
        Map<String, String> accessTokenAndRefreshTokenMap = JwtUtil.createAccessTokenAndRefreshToken(userid.toString());

        //从accessTokenAndRefreshTokenMap取出accessToken和refreshToken
        String accessToken = accessTokenAndRefreshTokenMap.get(jwtProperties.getAccessTokenName());
        String refreshToken = accessTokenAndRefreshTokenMap.get(jwtProperties.getRefreshTokenName());


        //将LoginUser对象存入Redis，证明已经登录了
        redisTemplate.opsForValue().set(RedisConstant.LOGIN_KEY_PREFIX +userid,loginUser,loginUserExpired, TimeUnit.MILLISECONDS);

        //将accessToken和refreshToken封装成TokenVO返回给前端
        TokenVO tokenVO = new TokenVO()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setNickName(loginUser.getUser().getNickName())
                .setAvatar(loginUser.getUser().getAvatar())
                .setUserName(loginUser.getUser().getUserName());

        //登录成功后添加登录日志，不需要设置id，因为mybatis-plus会自动为我们生成
//        String userIp = IpUtils.getIpAddr(request);
//        LoginLog loginLog = LoginLog.builder()
//                .username(userLoginDto.getUsername())
//                .ip(userIp)
//                .address(IpToAddressUtil.getCityInfo(userIp))
//                .browser(BrowserUtils.getBrowserName(request))
//                .os(BrowserUtils.getOsName(request))
//                .loginTime(LocalDateTime.now())
//                .build();
//        loginLogService.save(loginLog);


        return new ResponseResult(ResponseType.LOGIN_SUCCESS.getCode(),ResponseType.LOGIN_SUCCESS.getMessage(),tokenVO);
    }

}
