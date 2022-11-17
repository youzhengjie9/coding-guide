package com.coding.guide.mobile.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.coding.guide.common.converter.DelFlagConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户详细信息（写一些用户的扩展字段）
 *
 * @author youzhengjie
 * @date 2022/11/16 21:20:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_detail")
@EqualsAndHashCode
@Builder
@Accessors(chain = true)
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId("id")
    @ApiModelProperty(name = "id",value = "主键")
    @ExcelIgnore
    private Long id;


    /**
     * 关联的用户id
     */
    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "关联的用户id")
    @ExcelProperty("关联的用户id")
    private Long userId;








    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableLogic //逻辑删除（0代表未删除，1代表已删除）
    @ApiModelProperty(name = "delFlag",value = "删除标志（0代表未删除，1代表已删除）",example = "0")
    @TableField("del_flag")
    @ExcelProperty(value = "是否删除",converter = DelFlagConverter.class)
    private Integer delFlag;



}
