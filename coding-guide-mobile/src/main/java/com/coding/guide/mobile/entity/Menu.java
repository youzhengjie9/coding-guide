package com.coding.guide.mobile.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.coding.guide.common.converter.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单（权限）表实体类
 * @author youzhengjie 2022-10-05 15:40:55
 */
@TableName(value="sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Menu implements Serializable,Comparable<Menu> {

    private static final long serialVersionUID = 1L;


    @TableId("id")
    @JsonSerialize(using = ToStringSerializer.class) //解决雪花算法生成的id过长导致前端js精度丢失问题（也就是js拿到的数据和后端不一致问题）
    @ApiModelProperty(name = "id",value = "主键")
    @ExcelProperty("菜单id")
    private Long id;


    @TableField("parent_id")
    @JsonSerialize(using = ToStringSerializer.class) //解决雪花算法生成的id过长导致前端js精度丢失问题（也就是js拿到的数据和后端不一致问题）
    @ApiModelProperty("后台侧边栏。父菜单id,一级菜单为0")
    @ExcelProperty("父菜单id")
    private Long parentId;


    @TableField("menu_name")
    @ApiModelProperty("菜单/权限名称")
    @ExcelProperty("菜单名称")
    private String menuName;


    @TableField("path")
    @ApiModelProperty("vue路由地址（type=1才会生效，type=0和2不生效）")
    @ExcelProperty("路由地址")
    private String path;


    @TableField("component")
    @ApiModelProperty("动态路由要用到。views目录下的组件路径,自动会补上前缀‘../views’，这个前缀是固定的写法不能写到数据库里不然会报错（type=1才会生效，type=0和2不生效）")
    @ExcelProperty("组件路径")
    private String component;

    @TableField("status")
    @ApiModelProperty("菜单状态（0正常 1停用）")
    @ExcelProperty(value = "菜单状态",converter = StatusConverter.class)
    private Integer status;

    @TableField("visible")
    @ApiModelProperty("菜单显示状态（0显示 1隐藏）（type=0或者1才会生效，type=2不生效）")
    @ExcelProperty(value = "菜单显示状态",converter = VisibleConverter.class)
    private Integer visible;


    @TableField("perms")
    @ApiModelProperty("权限标识，比如sys:user:list(type=0设置为null即可，不会生效)")
    @ExcelProperty("权限标识")
    private String perms;

    @TableField("type")
    @ApiModelProperty("菜单类型。0：目录（点击后台侧边栏可以展开成下一级菜单的按钮）;1：菜单（点击后台侧边栏直接跳转vue路由组件的按钮）;2：按钮;菜单里面的按钮")
    @ExcelProperty(value = "菜单类型",converter = MenuTypeConverter.class)
    private Integer type;

    @TableField("icon")
    @ApiModelProperty("菜单图标（type=0或者1才会生效，type=2不生效）")
    @ExcelIgnore
    private String icon;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    @ExcelProperty(value = "创建时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    @TableField("update_time")
    @ApiModelProperty("最后一次修改时间")
    @ExcelProperty(value = "最后一次修改时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime updateTime;

    @TableLogic//逻辑删除
    @TableField("del_flag")
    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    @ExcelProperty(value = "删除标志",converter = DelFlagConverter.class)
    private Integer delFlag;


    @TableField("sort")
    @ApiModelProperty("前端菜单排序，默认值为1，1的优先级最高，排在最上面")
    @ExcelProperty("菜单排序")
    private int sort;

    @TableField("remark")
    @ApiModelProperty("备注")
    @ExcelProperty("备注")
    private String remark;

    @ApiModelProperty("当前菜单的下级菜单")
    @ExcelIgnore
    private List<Menu> children;

    //实现排序接口
    @Override
    public int compareTo(Menu o) {
        //升序
        return this.sort - o.getSort();
    }
}