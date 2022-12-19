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
 * 面试题浏览记录实体类
 *
 * @author youzhengjie
 * @date 2022/12/18 17:17:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_browse_record")
@Builder
@Accessors(chain = true)
public class QuestionBrowseRecord implements Serializable {

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
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "用户id")
    private Long userId;

    /**
     * 浏览的面试题的id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("question_id")
    @ApiModelProperty(name = "questionId",value = "浏览的面试题的id")
    private Long questionId;

    /**
     * 浏览时间
     */
    @TableField("browse_time")
    @ApiModelProperty(name = "browseTime",value = "浏览时间")
    private LocalDateTime browseTime;

}
