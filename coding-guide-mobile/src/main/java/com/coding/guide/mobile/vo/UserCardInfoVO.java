package com.coding.guide.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户资料卡信息
 *
 * @author youzhengjie
 * @date 2022/11/25 20:58:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserCardInfoVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "userName",value = "用户名",example = "root")
    private String userName;

    @ApiModelProperty(name = "nickName",value = "昵称",example = "我的昵称")
    private String nickName;

    @ApiModelProperty(name = "sex",value = "用户性别（0男，1女，2未知）",example = "0")
    private Integer sex;

    @ApiModelProperty(name = "avatar",value = "头像地址",example = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF")
    private String avatar;

    @ApiModelProperty(name = "likedCount",value = "获赞数")
    private String likedCount;

    @ApiModelProperty(name = "collectedCount",value = "被收藏数")
    private String collectedCount;

    @ApiModelProperty(name = "focusCount",value = "关注数")
    private String focusCount;

    @ApiModelProperty(name = "fansCount",value = "粉丝数")
    private String fansCount;

}
