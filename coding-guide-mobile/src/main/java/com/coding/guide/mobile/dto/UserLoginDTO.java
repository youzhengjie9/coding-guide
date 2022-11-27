package com.coding.guide.mobile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * UserLoginDto 用于前端登录表单传给controller用的
 *
 * @author youzhengjie
 * @date 2022/11/16 23:44:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Length(min = 5,max = 15,message = "帐号长度要在5-15位之间")
    @ApiModelProperty(name = "userName",value = "用户名",example = "root123")
    private String userName;
    /**
     * 密码
     */
    @Length(min = 5,max = 20,message = "密码长度要在5-20位之间")
    @ApiModelProperty(name = "password",value = "密码",example = "123456")
    private String password;

}
