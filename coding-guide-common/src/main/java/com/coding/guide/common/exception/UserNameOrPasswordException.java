package com.coding.guide.common.exception;

/**
 * 用户名或者密码不正确
 * @author youzhengjie 2022-09-27 23:26:19
 */
public class UserNameOrPasswordException extends RuntimeException {

    private static final String MSG="用户名或者密码不正确，请重新输入";

    public UserNameOrPasswordException() {
        super(MSG);
    }

    public UserNameOrPasswordException(String message) {
        super(message);
    }

}
