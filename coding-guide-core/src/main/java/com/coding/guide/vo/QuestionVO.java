package com.coding.guide.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
import java.util.List;

/**
 *
 *
 * @author youzhengjie
 * @date 2022/11/10 15:24:05
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId("id")
    @ApiModelProperty(name = "id",value = "主键")
    private Long id;

    /**
     * 面试题标题
     */
    @TableField("title")
    @ApiModelProperty(name = "title",value = "面试题标题")
    private String title;

    /**
     * 是否为推荐（0代表不推荐，1代表推荐）
     */
    @ApiModelProperty(name = "recommend",value = "是否为推荐（0代表不推荐，1代表推荐）")
    private int recommend;

    /**
     * 阅读数
     */
    @ApiModelProperty(name = "readCount",value = "阅读数")
    private int readCount;

    /**
     * 点赞数
     */
    @ApiModelProperty(name = "likeCount",value = "点赞数")
    private int likeCount;

    /**
     * 收藏数
     */
    @ApiModelProperty(name = "collectCount",value = "收藏数")
    private int collectCount;

    /**
     * 题目遇到数
     */
    @ApiModelProperty(name = "meetCount",value = "题目遇到数")
    private int meetCount;

    /**
     * 题目难度（分为1=简单、2=中等、3=较难、4=困难）
     */
    @ApiModelProperty(name = "difficulty",value = "题目难度（分为1=简单、2=中等、3=较难、4=困难）")
    private int difficulty;

    /**
     * 该面试题标签名称集合
     */
    @ApiModelProperty(name = "tagList",value = "该面试题标签名称集合")
    private List<String> tagList;

    /**
     * 发布时间
     */
    @ApiModelProperty(name = "publishTime",value = "发布时间")
    private LocalDateTime publishTime;



}
