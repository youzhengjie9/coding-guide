package com.coding.guide.common.utils;

import com.coding.guide.common.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JWT工具类
 *
 * @author youzhengjie
 * @date 2022/11/17 00:10:14
 */
@Component
@Slf4j
public class JwtUtil {

    private static JwtProperties jwtProperties;

    @Autowired
    public void setJwtProperties(JwtProperties jwtProperties) {
        JwtUtil.jwtProperties = jwtProperties;
    }

    /**
     * @return 随机的id
     */
    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * @return 同时返回access_token和refresh_token的map集合
     * 通过map集合.get(TokenConstant.ACCESSTOKEN)可以拿到access token
     * 通过map集合.get(TokenConstant.REFRESHTOKEN)可以拿到refresh token
     */
    public static Map<String,String> createAccessTokenAndRefreshToken(String subject){

        Map<String,String> tokenMap=new ConcurrentHashMap<>();

        String accessToken = createAccessToken(subject);

        String refreshToken = createRefreshToken(subject);

        tokenMap.put(jwtProperties.getAccessTokenName(),accessToken);
        tokenMap.put(jwtProperties.getRefreshTokenName(),refreshToken);

        return tokenMap;
    }

    /**
     *  根据用户id生成access_token
     * @param subject 例如userid
     * @return 返回access token
     */
    public static String createAccessToken(String subject){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + jwtProperties.getAccessTokenExpired(); //access_token过期时间

        return Jwts.builder()
                .setId(getUUID())              //唯一的ID
                .setSubject(subject)       //数据内容就存放到这里
                .setIssuer("youzhengjie")     // 签发者
                .setIssuedAt(now)      // 颁发token时间
                .setExpiration(new Date(expMillis))//token过期时间
                .signWith(SignatureAlgorithm.HS256,jwtProperties.getAccessTokenKey())
                .compact();
    }

    /**
     * 下面的代码已经自带了校验签名和过期时间，如果签名不正确或者过期了则直接会抛出异常
     * @param accessToken 解析access_token
     * @return
     */
    public static Claims parseAccessToken(String accessToken) {


        return Jwts
                .parser()
                .setSigningKey(jwtProperties.getAccessTokenKey())
                .parseClaimsJws(accessToken)
                .getBody();
    }

    /**
     *  根据用户id生成refresh_token
     * @param subject 例如userid
     * @return 返回refresh token
     */
    public static String createRefreshToken(String subject){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + jwtProperties.getRefreshTokenExpired(); //refresh_token过期时间

        return Jwts.builder()
                .setId(getUUID())              //唯一的ID
                .setSubject(subject)       //数据内容就存放到这里
                .setIssuer("youzhengjie")     // 签发者
                .setIssuedAt(now)      // 颁发token时间
                .setExpiration(new Date(expMillis))//token过期时间
                .signWith(SignatureAlgorithm.HS256,jwtProperties.getRefreshTokenKey())
                .compact();
    }

    /**
     * 下面的代码已经自带了校验签名和过期时间，如果签名不正确或者过期了则直接会抛出异常
     * @param refreshToken 解析refresh_token
     * @return 如果refreshToken过期了则返回null
     */
    public static Claims parseRefreshToken(String refreshToken) {

        return Jwts
                .parser()
                .setSigningKey(jwtProperties.getRefreshTokenKey())
                .parseClaimsJws(refreshToken)
                .getBody();
    }

    /**
     *  判断token是否可以被刷新（主要就是看refresh_token是否过期，如果没有过期则可以刷新token）
     * @param refreshToken
     * @return true就是refreshtoken可以使用，反之则不行
     */
    public static boolean canRefresh(String refreshToken){
        Claims claims = parseRefreshToken(refreshToken);
        return !isRefreshTokenExpired(claims);
    }

    /**
     * 去刷新token，本质上就是重新生成新的accesstoken和refreshtoken并替代掉前端localstorage的旧的accesstoken和refreshtoken
     * @param refreshToken
     * @return 如果不能刷新token，则返回null
     */
    public static Map<String, String> toRefreshToken(String refreshToken){
        //先判断refreshToken是否过期，看看能不能刷新token，否则返回null
        if(!canRefresh(refreshToken)){
            return null;
        }
        Claims claims = parseRefreshToken(refreshToken);
        String subject = claims.getSubject();

        return createAccessTokenAndRefreshToken(subject);//重新生成accesstoken和refreshtoken
    }

    /**
     *  判断access_token是否过期
     * @param claims
     * @return true就是过期了
     */
    public static boolean isAccessTokenExpired(Claims claims) {
        Date expiredDate = claims.getExpiration();
        return expiredDate.before(new Date());   //expiredDate < new Date()  true
    }

    /**
     *  判断refresh_token是否过期
     * @param claims
     * @return true就是过期了
     */
    public static boolean isRefreshTokenExpired(Claims claims) {
        Date expiredDate = claims.getExpiration();
        return expiredDate.before(new Date());   //expiredDate < new Date()  true
    }

    /**
     * 测试util
     * @param args
     */
    public static void main(String[] args) {
        String userid="123123999777";

        //1: 生成token
        Map<String, String> tokenMap = createAccessTokenAndRefreshToken(userid);
        String accesstoken = tokenMap.get(jwtProperties.getAccessTokenName());
        String refreshtoken = tokenMap.get(jwtProperties.getRefreshTokenName());

        log.warn(accesstoken);
        log.warn(refreshtoken);

        //2:解析token
        Claims c1 = parseAccessToken(accesstoken);

        Claims c2 = parseRefreshToken(refreshtoken);

        String s1 = c1.getSubject();
        System.out.println(s1);

        String s2 = c2.getSubject();
        System.out.println(s2);


        //3：刷新token
        Map<String, String> refreshTokenMap = toRefreshToken(refreshtoken);

        String accesstoken2 = refreshTokenMap.get(jwtProperties.getAccessTokenName());
        String refreshtoken2 = refreshTokenMap.get(jwtProperties.getRefreshTokenName());

        log.warn("------------------");
        log.warn(accesstoken2);
        log.warn(refreshtoken2);

        Claims claims1 = parseAccessToken(accesstoken2);

        Claims claims2 = parseRefreshToken(refreshtoken2);

        System.out.println(claims1.getSubject());

        System.out.println(claims2.getSubject());

    }


}