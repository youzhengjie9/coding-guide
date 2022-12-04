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
 * 面试题评论实体类
 *
 * @author youzhengjie
 * @date 2022/12/04 16:22:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_comment")
@Builder
@Accessors(chain = true)
public class QuestionComment implements Serializable {

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
     * 发送这条评论的用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "发送这条评论的用户id")
    private Long userId;

    /**
     * 这条评论所属的面试题id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("question_id")
    @ApiModelProperty(name = "questionId",value = "这条评论所属的面试题id")
    private Long questionId;

    /**
     * 评论内容（不能超过100字）
     */
    @TableField("content")
    @ApiModelProperty(name = "content",value = "评论内容（不能超过100字）")
    private String content;

    /**
     * 评论时间
     */
    @TableField("comment_time")
    @ApiModelProperty(name = "commentTime",value = "评论时间")
    private LocalDateTime commentTime;

    /**
     * 这条评论的回复数
     */
    @TableField("reply_count")
    @ApiModelProperty(name = "replyCount",value = "这条评论的回复数")
    private int replyCount;

    /**
     * 这条评论的点赞数
     */
    @TableField("like_count")
    @ApiModelProperty(name = "likeCount",value = "这条评论的点赞数")
    private int likeCount;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableLogic
    @TableField("del_flag")
    @ApiModelProperty(name = "delFlag",value = "删除标志（0代表未删除，1代表已删除）")
    private int delFlag;


}
