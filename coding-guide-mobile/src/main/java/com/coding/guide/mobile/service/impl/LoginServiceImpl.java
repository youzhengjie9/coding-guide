package com.coding.guide.mobile.service.impl;

import com.coding.guide.common.config.JwtProperties;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.dto.UserLoginDTO;
import com.coding.guide.mobile.security.SecurityUser;
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

    @Override
    public ResponseResult<TokenVO> login(UserLoginDTO userLoginDto, HttpServletRequest request) throws Throwable {

        //这个就是我们前端表单传入的UserDto（封装了前端提交的帐号密码），目的是为了后面检查帐号密码是否正确
        //--------------------
        //UsernamePasswordAuthenticationToken两个参数的构造方法就是用来分别传递帐号密码的。（这里我们一定要使用这个）
        //UsernamePasswordAuthenticationToken三个参数的构造方法才是用来证明用户已经登录。
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getUserName(),userLoginDto.getPassword());

        //1：authenticationManager.authenticate底层就是调用了UserDetailsService的loadUserByUserName方法，获取到UserDetails对象（也就是SecurityUser对象）
        //2：将usernamePasswordAuthenticationToken（前端传入的帐号密码）和loadUserByUsername中的userMapper.selectOne(lambdaQueryWrapper)方法查询的帐号密码进行比对，判断帐号密码输入是否正确。
        //2.1：如果是帐号不存在的话，就会在loadUserByUsername方法中抛出异常并且被AuthenticationEntryPointImpl方法捕获，返回（code：601）
        //2.2：如果是帐号存在，但是密码不正确的话，authenticationManager.authenticate方法就会在AuthenticationEntryPointImpl方法返回null给前端
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //------走到这一步，证明帐号密码都是正确的，可以给前端返回jwt token了（因为只有帐号密码全部正确才走的到这里）------
        // 认证成功就可以通过authenticate.getPrincipal()拿到SecurityUser对象
        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();
        Long userid = securityUser.getUser().getId();
        //如果redis中有该用户的securityUser对象则直接覆盖，这样就能更好的保证securityUser对象为最新的对象
        redisTemplate.opsForValue().set(RedisConstant.SECURITY_USER_KEY_PREFIX+userid,securityUser,3, TimeUnit.DAYS);

        //根据userid生成accessToken和refreshToken
        Map<String, String> accessTokenAndRefreshTokenMap = JwtUtil.createAccessTokenAndRefreshToken(userid.toString());

        //从accessTokenAndRefreshTokenMap取出accessToken和refreshToken
        String accessToken = accessTokenAndRefreshTokenMap.get(jwtProperties.getAccessTokenName());
        String refreshToken = accessTokenAndRefreshTokenMap.get(jwtProperties.getRefreshTokenName());

        //将accessToken和refreshToken和用户基本信息封装成TokenVO返回给前端
        TokenVO tokenVO = new TokenVO()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setNickName(securityUser.getUser().getNickName())
                .setAvatar(securityUser.getUser().getAvatar())
                .setUserName(securityUser.getUser().getUserName());

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
