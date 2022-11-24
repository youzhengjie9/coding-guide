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
 * 点赞面试题类
 *
 * @author youzhengjie
 * @date 2022/11/20 22:24:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_like")
@Builder //开启建造者模式
@Accessors(chain = true)
public class QuestionLike implements Serializable {

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
     * 被点赞的面试题id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("question_id")
    @ApiModelProperty(name = "questionId",value = "被点赞的面试题id")
    private Long questionId;

    /**
     * 点赞时间
     */
    @TableField("like_time")
    @ApiModelProperty(name = "likeTime",value = "点赞时间")
    private LocalDateTime likeTime;

}
