package com.coding.guide.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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

    public static Tag.Builder builder(){
        return new Tag.Builder();
    }

    //设计模式-建造者模式
    public static final class Builder{

        private Long id;

        private String tagName;

        private LocalDateTime createTime;

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

        public Tag build(){

            return new Tag()
                    .setId(id)
                    .setTagName(tagName)
                    .setCreateTime(createTime);
        }

    }

}
