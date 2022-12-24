package com.coding.guide.mobile.constant;

/**
 * 移动端-Redis常量
 *
 * @author youzhengjie
 * @date 2022/11/18 00:55:06
 */
public final class RedisConstant {

    /**
     * SecurityUser对象的redis的key前缀
     * 后面加上userid
     */
    public static final String SECURITY_USER_KEY_PREFIX="mobile:security:user:";

    /**
     * 手机验证码的redis的key前缀
     * 后面加上手机号即可
     */
    public static final String PHONE_CODE_KEY_PREFIX="mobile:phone:code:";

    /**
     * 面试题对象的redis的key前缀
     * 后面加上面试题id
     */
    public static final String QUESTION_DETAIL_KEY_PREFIX="mobile:question:detail:";

    /**
     * 保存面试题草稿的锁的key前缀
     * 后面加上accessToken
     */
    public static final String SAVE_QUESTION_DRAFT_LOCK_KEY_PREFIX="mobile:question:draft:lock:";
}
