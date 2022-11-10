package com.coding.guide.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 封装统一响应结果
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为null的字段不进行序列化
@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应状态码对应的信息提示
     */
    private String msg;

    /**
     * 返回给前端的数据
     */
    private T data;

    public ResponseResult() {

    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }


}
