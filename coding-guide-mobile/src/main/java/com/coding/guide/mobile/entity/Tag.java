package com.coding.guide.mobile.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 标签表
 *
 * @author youzhengjie
 * @date 2022/11/09 13:04:58
 */
@Data
@TableName("t_tag")
@Accessors(chain = true)
public class Tag implements Serializable {

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
     * 标签名称
     */
    @TableField("tag_name")
    @ApiModelProperty(name = "tagName",value = "标签名称")
    private String tagName;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty(name = "createTime",value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 排序,值越大优先级越高，越排在上面
     */
    @TableField("sort")
    @ApiModelProperty(name = "sort",value = "排序,值越大优先级越高，越排在上面")
    private int sort;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableLogic //逻辑删除（0代表未删除，1代表已删除）
    @TableField("del_flag")
    @ApiModelProperty(name = "delFlag",value = "删除标志（0代表未删除，1代表已删除）")
    private int delFlag;

    public static Builder builder(){
        return new Builder();
    }

    //设计模式-建造者模式
    public static final class Builder{

        private Long id;

        private String tagName;

        private LocalDateTime createTime;

        private int sort;

        private int delFlag;

        public Builder id(Long id){
            this.id=id;
            return this;
        }
        public Builder tagName(String tagName){
            this.tagName=tagName;
            return this;
        }
        public Builder createTime(LocalDateTime createTime){
            this.createTime=createTime;
            return this;
        }

        public Builder sort(int sort){
            this.sort=sort;
            return this;
        }

        public Builder delFlag(int delFlag){
            this.delFlag=delFlag;
            return this;
        }

        public Tag build(){

            return new Tag()
                    .setId(id)
                    .setTagName(tagName)
                    .setCreateTime(createTime)
                    .setSort(sort)
                    .setDelFlag(delFlag);
        }

    }

}
