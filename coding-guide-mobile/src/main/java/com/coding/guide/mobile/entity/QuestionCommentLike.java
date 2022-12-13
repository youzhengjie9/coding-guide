package com.coding.guide.mobile.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 点赞面试题评论实体类
 *
 * @author youzhengjie
 * @date 2022/12/12 23:01:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_comment_like")
@Builder
@Accessors(chain = true)
public class QuestionCommentLike implements Serializable {

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
     * 点赞者的用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "点赞者的用户id")
    private Long userId;

    /**
     * 被点赞的面试题评论id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("comment_id")
    @ApiModelProperty(name = "commentId",value = "被点赞的面试题评论id")
    private Long commentId;

    /**
     * 点赞时间
     */
    @TableField("like_time")
    @ApiModelProperty(name = "likeTime",value = "点赞时间")
    private LocalDateTime likeTime;

}
