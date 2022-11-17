package com.coding.guide.common.data;

import com.coding.guide.common.enums.ResponseType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 封装统一响应结果
 *
 * @author youzhengjie
 * @date 2022/11/17 00:17:55
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

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg=msg;
        this.data = data;
    }

    //泛型方法。快速封装成功的响应对象
    public static<D> ResponseResult<D> ok (D data){

        return new ResponseResult<D>()
                .setCode(ResponseType.SUCCESS.getCode())
                .setMsg(ResponseType.SUCCESS.getMessage())
                .setData(data);
    }

    //泛型方法。快速封装失败的响应对象
    public static<D> ResponseResult<D> fail (D data){

        return new ResponseResult<D>()
                .setCode(ResponseType.ERROR.getCode())
                .setMsg(ResponseType.ERROR.getMessage())
                .setData(data);
    }

}
