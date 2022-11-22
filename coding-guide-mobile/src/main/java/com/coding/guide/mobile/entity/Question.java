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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 面试题
 *
 * @author youzhengjie
 * @date 2022/11/08 18:03:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question")
@Builder //开启建造者模式
@Accessors(chain = true)
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId("id")
    @ApiModelProperty(name = "id",value = "主键")
    private Long id;

    /**
     * 发布面试题的用户id
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
     * 是否为推荐（0代表不推荐，1代表推荐）
     */
    @TableField("recommend")
    @ApiModelProperty(name = "recommend",value = "是否为推荐（0代表不推荐，1代表推荐）")
    private int recommend;

    /**
     * 文章是否公开（0代表私密，1代表公开）
     */
    @TableField("is_public")
    @ApiModelProperty(name = "isPublic",value = "文章是否公开（0代表私密，1代表公开）")
    private int isPublic;

    /**
     * 阅读数
     */
    @TableField("read_count")
    @ApiModelProperty(name = "readCount",value = "阅读数")
    private int readCount;

    /**
     * 点赞数
     */
    @TableField("like_count")
    @ApiModelProperty(name = "likeCount",value = "点赞数")
    private int likeCount;

    /**
     * 收藏数
     */
    @TableField("collect_count")
    @ApiModelProperty(name = "collectCount",value = "收藏数")
    private int collectCount;

    /**
     * 评论数
     */
    @TableField("comment_count")
    @ApiModelProperty(name = "commentCount",value = "评论数")
    private int commentCount;

    /**
     * 题目遇到数
     */
    @TableField("meet_count")
    @ApiModelProperty(name = "meetCount",value = "题目遇到数")
    private int meetCount;

    /**
     * 题目难度（分为1=简单、2=中等、3=较难、4=困难）
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
     * 发布时间
     */
    @TableField("publish_time")
    @ApiModelProperty(name = "publishTime",value = "发布时间")
    private LocalDateTime publishTime;

    /**
     * 最后一次修改时间
     */
    @TableField("update_time")
    @ApiModelProperty(name = "updateTime",value = "最后一次修改时间")
    private LocalDateTime updateTime;

    /**
     * 排序,值越大优先级越高，越排在上面
     */
    @TableField("sort")
    @ApiModelProperty(name = "sort",value = "排序,值越大优先级越高，越排在上面")
    private int sort;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableLogic //逻辑删除（0代表未删除，1代表已删除）
    @TableField("del_flag")
    @ApiModelProperty(name = "delFlag",value = "删除标志（0代表未删除，1代表已删除）")
    private int delFlag;


}
