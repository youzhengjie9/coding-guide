package com.coding.guide.mobile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * 面试题回复DTO
 *
 * @author youzhengjie
 * @date 2022/12/08 17:57:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class QuestionReplyDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 被回复的那条回复的id主键，用于记录这条回复到底回复了哪条回复（如果为0则说明回复评论,那么这个属性就没有任何作用,反之说明回复别人的回复,这个属性才有作用）
     */
    @ApiModelProperty(name = "repliedId",value = "被回复的那条回复的id主键（如果为0则说明回复评论,那么这个属性就没有任何作用,反之说明回复别人的回复,这个属性才有作用）")
    private Long repliedId;

    /**
     * 这条回复所属的评论id
     */
    @ApiModelProperty(name = "commentId",value = "这条回复所属的评论id")
    private Long commentId;

    /**
     * 评论内容（不能为空、不能超过100字）
     */
    @ApiModelProperty(name = "content",value = "评论内容（不能为空、不能超过100字）")
    @Length(min = 1,max = 100,message = "评论内容（不能为空、不能超过100字）")
    private String content;

}
