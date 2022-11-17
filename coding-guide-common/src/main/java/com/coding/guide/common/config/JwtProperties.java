package com.coding.guide.common.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * accessToken：过期时间短、正常访问接口只需要在Header加上这个token即可
 * refreshToken：过期时间长、只有accessToken过期了才会使用这个refreshToken去刷新令牌。
 *
 * @author youzhengjie
 * @date 2022/11/17 00:05:54
 */
@ConfigurationProperties(
        prefix = "jwt.config"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtProperties {

    /**
     * accessToken的名称（和前端存储在localstorage的token名称一致）
     */
    private String accessTokenName;

    /**
     * accessToken密钥
     */
    private String accessTokenKey;

    /**
     * accessToken过期时间，单位是（毫秒）。一小时=3600000毫秒
     */
    private long accessTokenExpired;

    /**
     * refreshToken的名称（和前端存储在localstorage的token名称一致）
     */
    private String refreshTokenName;

    /**
     * refreshToken密钥
     */
    private String refreshTokenKey;

    /**
     * refreshToken过期时间，单位是（毫秒）。一小时=3600000毫秒
     */
    private long refreshTokenExpired;

}
