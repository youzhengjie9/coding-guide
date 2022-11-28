package com.coding.guide.mobile.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 简单的用户信息VO
 *
 * @author youzhengjie
 * @date 2022/11/27 19:00:20
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserInfoVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 发布者用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(name = "publisherId",value = "发布者用户id")
    private Long publisherId;

    @ApiModelProperty(name = "nickName",value = "昵称")
    private String nickName;

    @ApiModelProperty(name = "avatar",value = "头像地址")
    private String avatar;

    @ApiModelProperty(name = "levelFormat",value = "积分等级格式化字符串（例如：Lv1-倔强青铜）")
    private String levelFormat;

    @ApiModelProperty(name = "backgroundColor",value = "背景颜色,一定要为rgb格式（例如：rgb(79 121 222)）")
    private String backgroundColor;

}
