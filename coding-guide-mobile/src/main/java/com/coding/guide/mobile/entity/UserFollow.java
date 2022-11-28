package com.coding.guide.mobile.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户关注实体类
 *
 * @author youzhengjie
 * @date 2022/11/28 09:03:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_follow")
@EqualsAndHashCode
@Builder
@Accessors(chain = true)
public class UserFollow implements Serializable {

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
     * 关注者用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "关注者用户id")
    private Long userId;

    /**
     * 被关注者用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("follow_user_id")
    @ApiModelProperty(name = "followUserId",value = "被关注者用户id")
    private Long followUserId;

    /**
     * 关注时间
     */
    @TableField("follow_time")
    @ApiModelProperty(name = "followTime",value = "关注时间")
    private LocalDate followTime;


}
