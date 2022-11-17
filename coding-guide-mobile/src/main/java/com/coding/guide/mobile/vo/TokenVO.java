package com.coding.guide.mobile.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 封装Token
 *
 * @author youzhengjie
 * @date 2022/11/16 23:43:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Builder
public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String nickName;

    private String avatar;

    private String accessToken;

    private String refreshToken;


}
