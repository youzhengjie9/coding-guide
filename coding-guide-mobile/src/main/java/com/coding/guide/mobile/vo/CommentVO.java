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
 * 评论实体类VO
 *
 * @author youzhengjie
 * @date 2022/12/04 01:12:58
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(name = "id",value = "主键")
    private Long id;

    /**
     * 发送这条评论的用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(name = "userId",value = "发送这条评论的用户id")
    private Long userId;

    /**
     * 发送这条评论的用户昵称
     */
    @ApiModelProperty(name = "nickName",value = "发送这条评论的用户昵称")
    private String nickName;

    /**
     * 发送这条评论的用户头像
     */
    @ApiModelProperty(name = "avatar",value = "发送这条评论的用户头像")
    private String avatar;

    /**
     * 评论内容
     */
    @ApiModelProperty(name = "content",value = "评论内容")
    private String content;

    /**
     * 评论时间
     */
    @ApiModelProperty(name = "commentTime",value = "评论时间",example = "2022-12-3 16:30")
    private LocalDateTime commentTime;

    /**
     * 这条评论的回复数
     */
    @ApiModelProperty(name = "replyCount",value = "这条评论的回复数")
    private Integer replyCount;

    /**
     * 这条评论的点赞数
     */
    @ApiModelProperty(name = "likeCount",value = "这条评论的点赞数")
    private Integer likeCount;

}
