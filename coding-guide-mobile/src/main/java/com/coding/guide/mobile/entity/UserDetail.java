package com.coding.guide.mobile.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.coding.guide.common.converter.DelFlagConverter;
import com.coding.guide.common.converter.LocalDateConverter;
import com.coding.guide.common.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 移动端用户详细信息（写一些用户的扩展字段）
 *
 * @author youzhengjie
 * @date 2022/11/16 21:20:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_detail")
@EqualsAndHashCode
@Builder
@Accessors(chain = true)
public class UserDetail implements Serializable {

    @Serial
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
     * 邮箱
     */
    @TableField("email")
    @ApiModelProperty(name = "email",value = "用户邮箱",example = "1550324080@qq.com")
    @ExcelProperty("用户邮箱")
    private String email;
    /**
     * 手机号
     */
    @TableField("phone")
    @ApiModelProperty(name = "phone",value = "用户手机号",example = "18420161234")
    @ExcelProperty("用户手机号")
    private String phone;

    /**
     * 所在国家
     */
    @TableField("county")
    @ApiModelProperty(name = "county",value = "所在国家")
    @ExcelProperty("所在国家")
    private String county;
    /**
     * 所在省
     */
    @TableField("province")
    @ApiModelProperty(name = "province",value = "所在省")
    @ExcelProperty("所在省")
    private String province;
    /**
     * 所在市
     */
    @TableField("city")
    @ApiModelProperty(name = "city",value = "所在市")
    @ExcelProperty("所在市")
    private String city;
    /**
     * 学校
     */
    @TableField("school")
    @ApiModelProperty(name = "school",value = "学校")
    @ExcelProperty("学校")
    private String school;

    /**
     * 生日
     */
    @TableField("birthday")
    @ApiModelProperty(name = "birthday",value = "生日")
    @ExcelProperty(value = "生日",converter = LocalDateConverter.class) //导出该属性，并且指定LocalDateConverter
    private LocalDate birthday;

    /**
     * 简介
     */
    @TableField("intro")
    @ApiModelProperty(name = "intro",value = "简介")
    @ExcelProperty("简介")
    private String intro;

    /**
     * 最后一次修改时间
     */
    @TableField("update_time")
    @ApiModelProperty(name = "updateTime",value = "最后一次修改时间",example = "2022-05-20 10:20:30")
    @ExcelProperty(value = "最后一次修改时间",converter = LocalDateTimeConverter.class)//导出该属性，并且指定LocalDateTimeConverter
    private LocalDateTime updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableLogic //逻辑删除（0代表未删除，1代表已删除）
    @ApiModelProperty(name = "delFlag",value = "删除标志（0代表未删除，1代表已删除）",example = "0")
    @TableField("del_flag")
    @ExcelProperty(value = "是否删除",converter = DelFlagConverter.class)
    private Integer delFlag;



}
