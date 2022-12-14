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
 * 点赞面试题回复实体类
 *
 * @author youzhengjie
 * @date 2022/12/14 17:41:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_reply_like")
@Builder
@Accessors(chain = true)
public class QuestionReplyLike implements Serializable {

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
     * 被点赞的面试题回复id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("reply_id")
    @ApiModelProperty(name = "replyId",value = "被点赞的面试题回复id")
    private Long replyId;

    /**
     * 点赞时间
     */
    @TableField("like_time")
    @ApiModelProperty(name = "likeTime",value = "点赞时间")
    private LocalDateTime likeTime;

}
