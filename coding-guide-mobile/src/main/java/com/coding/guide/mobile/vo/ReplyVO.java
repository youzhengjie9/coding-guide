package com.coding.guide.mobile.vo;

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
 * 回复实体类VO
 *
 * @author youzhengjie
 * @date 2022/12/04 01:34:47
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(name = "id",value = "主键")
    private Long id;

    /**
     * 发送这条回复的用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(name = "userId",value = "发送这条回复的用户id")
    private Long userId;

    /**
     * 发送这条回复的用户昵称
     */
    @ApiModelProperty(name = "nickName",value = "发送这条回复的用户昵称")
    private String nickName;

    /**
     * 发送这条回复的用户头像
     */
    @ApiModelProperty(name = "avatar",value = "发送这条回复的用户头像")
    private String avatar;

    /**
     * 回复内容
     */
    @ApiModelProperty(name = "content",value = "回复内容")
    private String content;

    /**
     * 回复时间
     */
    @ApiModelProperty(name = "replyTime",value = "回复时间",example = "2022-12-3 16:30")
    private LocalDateTime replyTime;

    /**
     * 这条回复的点赞数
     */
    @ApiModelProperty(name = "likeCount",value = "这条回复的点赞数")
    private Integer likeCount;

}
