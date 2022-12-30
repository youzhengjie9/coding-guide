package com.coding.guide.mobile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

/**
 * 绑定邮箱DTO
 * @author youzhengjie
 * @date 2022/12/31 00:48:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class BindEmailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(name = "email",value = "qq邮箱",example = "1550324080@qq.com")
    private String email;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(name = "code",value = "绑定qq邮箱的验证码")
    private String code;

}
