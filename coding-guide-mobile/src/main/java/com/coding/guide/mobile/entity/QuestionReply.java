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
 * 面试题回复实体类
 *
 * @author youzhengjie
 * @date 2022/12/04 16:51:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_reply")
@Builder
@Accessors(chain = true)
public class QuestionReply implements Serializable {

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
     * 发送这条回复的用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "发送这条回复的用户id")
    private Long userId;

    /**
     * 被回复的用户id（如果为0则说明回复评论,反之说明回复别人的回复）
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("replied_user_id")
    @ApiModelProperty(name = "repliedUserId",value = "被回复的用户id")
    private Long repliedUserId;

    /**
     * 这条回复所属的评论id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("comment_id")
    @ApiModelProperty(name = "commentId",value = "这条回复所属的评论id")
    private Long commentId;

    /**
     * 回复内容（不能为空、不能超过100字）
     */
    @TableField("content")
    @ApiModelProperty(name = "content",value = "回复内容（不能为空、不能超过100字）")
    private String content;

    /**
     * 回复时间
     */
    @TableField("reply_time")
    @ApiModelProperty(name = "replyTime",value = "回复时间")
    private LocalDateTime replyTime;

    /**
     * 这条回复的点赞数
     */
    @TableField("like_count")
    @ApiModelProperty(name = "likeCount",value = "这条回复的点赞数")
    private int likeCount;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableLogic
    @TableField("del_flag")
    @ApiModelProperty(name = "delFlag",value = "删除标志（0代表未删除，1代表已删除）")
    private int delFlag;

}
