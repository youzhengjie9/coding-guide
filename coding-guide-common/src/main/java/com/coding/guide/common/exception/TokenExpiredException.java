package com.coding.guide.common.exception;


/**
 * 令牌过期异常
 *
 * @author youzhengjie
 * @date 2022/10/24 22:52:12
 */
public class TokenExpiredException extends RuntimeException {

    private static final String MSG="Token过期了";

    public TokenExpiredException() {
        super(MSG);
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
