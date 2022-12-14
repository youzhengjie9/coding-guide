package com.coding.guide.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 标签VO
 *
 * @author youzhengjie
 * @date 2022/11/12 19:30:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TagVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
//    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(name = "id",value = "主键")
    private Long id;

    /**
     * 标签名称
     */
    @ApiModelProperty(name = "tagName",value = "标签名称")
    private String tagName;


}
