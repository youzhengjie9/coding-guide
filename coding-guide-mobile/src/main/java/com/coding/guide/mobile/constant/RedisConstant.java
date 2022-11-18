package com.coding.guide.mobile.constant;

/**
 * 移动端-Redis常量
 *
 * @author youzhengjie
 * @date 2022/11/18 00:55:06
 */
public final class RedisConstant {

    /**
     * redis key前缀。记录每一个登录用户的信息
     */
    public static final String LOGIN_KEY_PREFIX="mobile:login:user:";

    /**
     * 手机验证码的key，后面加上手机号即可
     */
    public static final String PHONE_CODE_KEY_PREFIX="mobile:phone:code:";




}
