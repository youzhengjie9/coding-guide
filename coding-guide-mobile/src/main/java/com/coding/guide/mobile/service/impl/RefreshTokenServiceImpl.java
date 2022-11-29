package com.coding.guide.mobile.service.impl;

import com.coding.guide.common.config.JwtProperties;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.JwtUtil;
import com.coding.guide.mobile.service.RefreshTokenService;
import com.coding.guide.mobile.vo.TokenVO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 刷新令牌服务impl
 *
 * @author youzhengjie
 * @date 2022/11/19 01:24:33
 */
@Service
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private JwtProperties jwtProperties;

    private RedisTemplate redisTemplate;
    @Autowired
    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ResponseResult<TokenVO> refresh(String refreshToken) {

        //1：判断前端传过来的refreshToken是否合法、是否过期
        if(JwtUtil.canRefresh(refreshToken)){
            //如果可以刷新token的话
            Claims claims = JwtUtil.parseRefreshToken(refreshToken);

            String subject = claims.getSubject();

            Map<String, String> accessTokenAndRefreshTokenMap = JwtUtil.createAccessTokenAndRefreshToken(subject);

            //从accessTokenAndRefreshTokenMap取出accessToken和refreshToken
            String newAccessToken = accessTokenAndRefreshTokenMap.get(jwtProperties.getAccessTokenName());
            String newRefreshToken = accessTokenAndRefreshTokenMap.get(jwtProperties.getRefreshTokenName());

            //将accessToken和refreshToken封装成TokenVO返回给前端
            TokenVO tokenVO = new TokenVO()
                    .setAccessToken(newAccessToken)
                    .setRefreshToken(newRefreshToken);
            return new ResponseResult<>
                    (ResponseType.REFRESH_TOKEN_SUCCESS.getCode(),ResponseType.REFRESH_TOKEN_SUCCESS.getMessage(),tokenVO);
        }
        return new ResponseResult<>(ResponseType.REFRESH_TOKEN_ERROR.getCode(),ResponseType.REFRESH_TOKEN_ERROR.getMessage(),null);

    }
}
