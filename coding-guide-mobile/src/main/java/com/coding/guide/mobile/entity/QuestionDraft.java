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
import java.time.LocalDateTime;

/**
 * 面试题草稿
 *
 * @author youzhengjie
 * @date 2022/11/08 18:03:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_draft")
@Builder
@Accessors(chain = true)
public class QuestionDraft implements Serializable {

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
     * 发布面试题草稿的用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "发布面试题的用户id")
    private Long userId;

    /**
     * 面试题标题
     */
    @TableField("title")
    @ApiModelProperty(name = "title",value = "面试题标题")
    private String title;

    /**
     * 面试题内容
     */
    @TableField("content")
    @ApiModelProperty(name = "content",value = "面试题内容")
    private String content;

    /**
     * 是否允许评论（0代表不允许评论，1代表允许评论）
     */
    @TableField("allow_comment")
    @ApiModelProperty(name = "allowComment",value = "是否允许评论（0代表不允许评论，1代表允许评论）")
    private int allowComment;

    /**
     * 文章是否公开（0代表私密，1代表公开）
     */
    @TableField("is_public")
    @ApiModelProperty(name = "isPublic",value = "文章是否公开（0代表私密，1代表公开）")
    private int isPublic;

    /**
     * 题目难度（分为1=简单、2=中等、3=较难、4=困难 ）
     */
    @TableField("difficulty")
    @ApiModelProperty(name = "difficulty",value = "题目难度（分为1=简单、2=中等、3=较难、4=困难）")
    private int difficulty;

    /**
     * 该面试题标签名称字符串，用‘,’分隔
     */
    @TableField("tags")
    @ApiModelProperty(name = "tags",value = "该面试题标签名称字符串，用‘,’分隔")
    private String tags;

    /**
     * 保存草稿时间
     */
    @TableField("save_time")
    @ApiModelProperty(name = "saveTime",value = "保存草稿时间")
    private LocalDateTime saveTime;

    /**
     * 最后一次修改草稿时间
     */
    @TableField("update_time")
    @ApiModelProperty(name = "updateTime",value = "最后一次修改草稿时间")
    private LocalDateTime updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableLogic
    @TableField("del_flag")
    @ApiModelProperty(name = "delFlag",value = "删除标志（0代表未删除，1代表已删除）")
    private int delFlag;


}
