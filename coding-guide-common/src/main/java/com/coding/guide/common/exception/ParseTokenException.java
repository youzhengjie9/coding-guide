package com.coding.guide.common.exception;

/**
 * 解析token失败异常
 * @author youzhengjie 2022-09-28 17:15:23
 */
public class ParseTokenException extends RuntimeException{

    private static final String MSG="解析token失败，请检查token是否正确";

    public ParseTokenException() {
        super(MSG);
    }

    public ParseTokenException(String message) {
        super(message);
    }
}
