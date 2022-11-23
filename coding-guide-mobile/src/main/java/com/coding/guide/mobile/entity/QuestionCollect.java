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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收藏面试题类
 *
 * @author youzhengjie
 * @date 2022/11/22 23:23:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_collect")
@Builder //开启建造者模式
@Accessors(chain = true)
public class QuestionCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId("id")
    @ApiModelProperty(name = "id",value = "主键")
    private Long id;

    /**
     * 收藏者的用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "收藏者的用户id")
    private Long userId;

    /**
     * 被收藏的面试题id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("question_id")
    @ApiModelProperty(name = "questionId",value = "被收藏的面试题id")
    private Long questionId;

    /**
     * 收藏时间
     */
    @TableField("collect_time")
    @ApiModelProperty(name = "collectTime",value = "收藏时间")
    private LocalDateTime collectTime;

}
