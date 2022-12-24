package com.coding.guide.mobile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

/**
 * 面试题DTO
 *
 * @author youzhengjie
 * @date 2022/12/24 20:06:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class QuestionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Length(min = 5,max = 100,message = "面试题标题必须为5-100个字")
    @ApiModelProperty(name = "title",value = "面试题标题")
    private String questionTitle;

    @Length(min = 5,message = "面试题内容必须大于5个字")
    @ApiModelProperty(name = "content",value = "面试题内容")
    private String questionContent;

    @ApiModelProperty(name = "allowComment",value = "是否允许评论（false代表不允许评论，true代表允许评论）")
    private Boolean allowComment;

    @ApiModelProperty(name = "isPublic",value = "文章是否公开（0代表私密，1代表公开）")
    private String isPublic;

    @NotBlank(message = "题目难度不能为空")
    @ApiModelProperty(name = "difficulty",value = "被选择出来的题目难度名称（分为简单、中等、较难、困难）")
    private String difficulty;

    @ApiModelProperty(name = "tags",value = "该面试题标签名称字符串，用‘,’分隔")
    private String tags;
}
