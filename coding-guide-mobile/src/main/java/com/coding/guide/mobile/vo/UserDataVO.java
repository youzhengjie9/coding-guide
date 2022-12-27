package com.coding.guide.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户数据VO（用于展示到编辑资料页面）
 *
 * @author youzhengjie
 * @date 2022/12/27 00:16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class UserDataVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "avatar",value = "头像地址",example = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF")
    private String avatar;

    @ApiModelProperty(name = "nickName",value = "昵称",example = "我的昵称")
    private String nickName;

    @ApiModelProperty(name = "sex",value = "用户性别（0男，1女，2未知）",example = "0")
    private String sex;

    @ApiModelProperty(name = "intro",value = "简介")
    private String intro;

    @ApiModelProperty(name = "birthday",value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(name = "address",value = "地区（格式为：国家-省-市）")
    private String address;

    @ApiModelProperty(name = "school",value = "学校")
    private String school;

    @ApiModelProperty(name = "phone",value = "用户手机号",example = "18420161234")
    private String phone;

    @ApiModelProperty(name = "email",value = "用户邮箱",example = "1550324080@qq.com")
    private String email;

}
