package com.coding.guide.mobile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * 用户注册dto
 *
 * @author youzhengjie
 * @date 2022/11/17 23:31:40
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Data
public class UserRegisterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(min = 3,max = 15,message = "帐号长度要在3-15位之间")
    @ApiModelProperty(name = "userName",value = "用户名",example = "root")
    private String userName;

    @Length(min = 5,max = 20,message = "密码长度要在5-20位之间")
    @ApiModelProperty(name = "password",value = "密码",example = "123456")
    private String password;

    @Length(min = 5,max = 20,message = "确认密码长度要在5-20位之间")
    @ApiModelProperty(name = "confirmPassword",value = "确认密码",example = "123456")
    private String confirmPassword;

    @Length(min = 11,max = 11,message = "手机号必须为11位数字")
    @ApiModelProperty(name = "phone",value = "用户手机号",example = "18420161234")
    private String phone;

    //手机验证码长度必须为6位
    @Length(min = 6,max = 6,message = "验证码长度必须为6位")
    @ApiModelProperty(name = "code",value = "手机验证码",example = "172621")
    private String code;


}
