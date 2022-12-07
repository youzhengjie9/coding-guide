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
 * 面试题回复实体类VO
 *
 * @author youzhengjie
 * @date 2022/12/04 01:34:47
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionReplyVO implements Serializable {

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
     * 回复内容（不能超过100字）
     */
    @ApiModelProperty(name = "content",value = "回复内容（不能超过100字）")
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

    /**
     * 被回复的用户id（如果为0则说明回复评论,那么这个属性就没有任何作用,反之说明回复别人的回复,这个属性才有作用）
     */
    @ApiModelProperty(name = "repliedUserId",value = "被回复的用户id")
    private Long repliedUserId;

    /**
     * 被回复的用户昵称（如果repliedUserId为0则说明回复评论,那么这个属性就没有任何作用,反之说明回复别人的回复,这个属性才有作用）
     */
    @ApiModelProperty(name = "repliedUserId",value = "被回复的用户id")
    private String repliedNickName;

}
