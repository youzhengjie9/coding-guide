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
 * 写面试题评论dto
 *
 * @author youzhengjie
 * @date 2022/12/08 17:57:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class WriteQuestionCommentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "questionId",value = "这条评论所属的面试题id")
    private Long questionId;

    @ApiModelProperty(name = "content",value = "评论内容（不能为空、不能超过100字）")
    @Length(min = 1,max = 100,message = "评论内容（不能为空、不能超过100字）")
    private String content;

}
