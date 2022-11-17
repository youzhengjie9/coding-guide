package com.coding.guide.mobile.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.coding.guide.common.converter.DelFlagConverter;
import com.coding.guide.common.converter.LocalDateTimeConverter;
import com.coding.guide.common.converter.StatusConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 角色
 * @author youzhengjie
 * @date 2022/10/13 12:04:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
@EqualsAndHashCode
@Builder
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * mp会自动为@TableId("id")属性生成id（默认是雪花算法生成的分布式id）。
     */
    @TableId("id")
    @JsonSerialize(using = ToStringSerializer.class) //解决雪花算法生成的id过长导致前端js精度丢失问题（也就是js拿到的数据和后端不一致问题）
    @ApiModelProperty(name = "id",value = "主键")
    @ExcelIgnore
    private Long id;

    @TableField("name")
    @ApiModelProperty("角色名称，比如管理员")
    @ExcelProperty("角色名称")
    private String name;

    @TableField("role_key")
    @ApiModelProperty("角色关键字，比如admin")
    @ExcelProperty("角色关键字")
    private String roleKey;

    @TableField("status")
    @ApiModelProperty("角色状态（0正常 1停用）")
    @ExcelProperty(value = "角色状态",converter = StatusConverter.class)
    private Integer status;

    @TableLogic
    @TableField("del_flag")
    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    @ExcelProperty(value = "删除标志",converter = DelFlagConverter.class)
    private int delFlag;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    @ExcelProperty(value = "创建时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    @TableField("update_time")
    @ApiModelProperty("最后一次修改时间")
    @ExcelProperty(value = "最后一次修改时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime updateTime;

    @TableField("remark")
    @ApiModelProperty("备注")
    @ExcelProperty("备注")
    private String remark;

}
