package com.coding.guide.mobile.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.coding.guide.common.converter.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 移动端用户表(User)实体类
 *
 * @author youzhengjie
 * @date 2022/11/17 18:35:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
@EqualsAndHashCode
@Builder
@Accessors(chain = true)
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
    * 主键
    * mp会自动为@TableId("id")属性生成id（默认是雪花算法生成的分布式id）。
    */
    @JsonSerialize(using = ToStringSerializer.class) //解决雪花算法生成的id过长导致前端js精度丢失问题（也就是js拿到的数据和后端不一致问题）
    @TableId("id")
    @ApiModelProperty(name = "id",value = "主键")
    @ExcelIgnore
    private Long id;
    /**
    * 用户名
    */
    @TableField("user_name")
    @ApiModelProperty(name = "userName",value = "用户名",example = "root")
    @ExcelProperty("用户名")
    private String userName;
    /**
     * 昵称
     */
    @TableField("nick_name")
    @ApiModelProperty(name = "nickName",value = "昵称",example = "我的昵称")
    @ExcelProperty("昵称")
    private String nickName;
    /**
    * 密码
    */
    @TableField("password")
    @ApiModelProperty(name = "password",value = "密码",example = "123456")
    @ExcelIgnore //easy-excel忽视这个属性（也就是不导出这个属性）
    private String password;
    /**
    * 账号状态（0正常 1停用）
    */
    @TableField("status")
    @ApiModelProperty(name = "status",value = "用户状态（0正常 1停用）",example = "0")
    @ExcelProperty(value = "用户状态",converter = StatusConverter.class)
    private Integer status;
    /**
    * 用户性别（0男，1女，2未知）
    */
    @TableField("sex")
    @ApiModelProperty(name = "sex",value = "用户性别（0男，1女，2未知）",example = "0")
    @ExcelProperty(value = "用户性别",converter = UserSexConverter.class)
    private Integer sex;
    /**
    * 头像地址
    */
    @TableField("avatar")
    @ApiModelProperty(name = "avatar",value = "头像地址",example = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF")
    @ExcelIgnore //easy-excel忽视这个属性（也就是不导出这个属性）
    private String avatar;

    /**
     * 用户积分
     */
    @TableField("integral")
    @ApiModelProperty(name = "integral",value = "用户积分",example = "0")
    @ExcelProperty(value = "用户积分")
    private Integer integral;

    /**
     * 用户钱包余额
     */
    @TableField("money")
    @ApiModelProperty(name = "money",value = "用户钱包余额",example = "0.00")
    @ExcelProperty(value = "用户钱包余额")
    private BigDecimal money;

    /**
    * 创建时间
    */
    @TableField("create_time")
    @ApiModelProperty(name = "createTime",value = "创建时间",example = "2022-01-10")
    @ExcelProperty(value = "创建时间",converter = LocalDateConverter.class) //导出该属性，并且指定LocalDateConverter
    private LocalDate createTime;
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