package com.coding.guide.mobile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户资料dto（用于编辑资料页面修改数据传输的类）
 *
 * @author youzhengjie
 * @date 2022/12/27 19:53:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class UserDataDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "avatarBase64",value = "头像的base64编码（由前端传递）")
    private String avatarBase64;

    @ApiModelProperty(name = "nickName",value = "昵称",example = "我的昵称")
    private String nickName;

    @ApiModelProperty(name = "sex",value = "用户性别（0男，1女，2未知）",example = "0")
    private String sex;

    @ApiModelProperty(name = "intro",value = "简介")
    private String intro;

    @ApiModelProperty(name = "birthday",value = "生日")
    private String birthday;

    @ApiModelProperty(name = "address",value = "地区（格式为：国家-省-市）")
    private String address;

    @ApiModelProperty(name = "school",value = "学校")
    private String school;

    @ApiModelProperty(name = "phone",value = "用户手机号",example = "18420161234")
    private String phone;

}
