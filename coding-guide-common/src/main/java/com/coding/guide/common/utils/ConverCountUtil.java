package com.coding.guide.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 转换数量工具类
 * 例如：1234=>1234 ; 233300==>23.3万 ; 351234000==>3.5亿
 *
 * @author youzhengjie
 * @date 2022/11/26 15:58:45
 */
@Slf4j
public final class ConverCountUtil {

    /**
     * 转换
     *
     * @param count 数
     * @return {@link String}
     */
    public static String conver(final Long count){
        //1:小于10000则直接展示
        if(count < 10000){
            return count.toString();
        }
        BigDecimal tenThousand = new BigDecimal(10000);
        //2:大于等于10000，则把数据转换成以“万”作单位的数据（例如：200000 ===> 20.0万，110000000===>11000.0万）
        var wan  = new BigDecimal(count).divide(tenThousand);
        //保留1位小数,向上取整。2.3513变成2.4
        wan=wan.setScale(1,RoundingMode.HALF_UP);
        //3:判断要转换成什么成单位（“万”或者“亿”）
        int select=wan.intValue()/10000;
        return switch (select){
            //如果为0，则说明单位是“万”
            case 0 -> wan +"万";
            //除了0，单位就是“亿”。将“万”转成“亿”
            default -> {
                var yi=wan.divide(tenThousand);
                //保留1位小数,向上取整。2.3513变成2.4
                yi=yi.setScale(1,RoundingMode.HALF_UP);
                yield yi+"亿";
            }
        };
    }

    public static void main(String[] args) {

        //测试示例：
        String conver1 = ConverCountUtil.conver(1234L);
        String conver2 = ConverCountUtil.conver(233300L);
        String conver3 = ConverCountUtil.conver(351234000L);

        //1234
        log.info(conver1);
        //23.3万
        log.info(conver2);
        //3.5亿
        log.info(conver3);

    }

}
