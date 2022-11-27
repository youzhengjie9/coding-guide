package com.coding.guide.mobile.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 积分等级实体类
 *
 * @author youzhengjie
 * @date 2022/11/27 22:21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_integral_level")
@Builder
@Accessors(chain = true)
public class IntegralLevel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId("id")
    @ApiModelProperty(name = "id",value = "主键")
    private Long id;


    /**
     * 积分等级
     */
    @TableField("level")
    @ApiModelProperty(name = "level",value = "积分等级（例如：1）")
    private int level;

    /**
     * 最低所需积分
     */
    @TableField("lowest_integral")
    @ApiModelProperty(name = "lowestIntegral",value = "最低所需积分")
    private int lowestIntegral;

    /**
     * 等级描述
     */
    @TableField("describe")
    @ApiModelProperty(name = "describe",value = "等级描述")
    private String describe;

    /**
     * 背景颜色,一定要为rgb格式（例如：rgb(79 121 222)）
     */
    @TableField("background_color")
    @ApiModelProperty(name = "backgroundColor",value = "背景颜色,一定要为rgb格式（例如：rgb(79 121 222)）")
    private String backgroundColor;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableLogic //逻辑删除（0代表未删除，1代表已删除）
    @TableField("del_flag")
    @ApiModelProperty(name = "delFlag",value = "删除标志（0代表未删除，1代表已删除）")
    private int delFlag;

}
