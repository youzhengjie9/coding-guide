package com.coding.guide.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

/**
 * 转换工具类
 *
 * @author youzhengjie
 * @date 2022/11/26 15:58:45
 */
@Slf4j
public final class ConverUtil {

    public static final String PROVINCE_KEY ="province";
    public static final String CITY_KEY ="city";
    public static final String AREA_KEY ="area";

    /**
     * 转换数量
     * 例如：1234=>1234 ; 233300==>23.3万 ; 351234000==>3.5亿
     * @param count 数
     * @return {@link String}
     */
    public static String converCount(final Long count){
        if(Objects.isNull(count)){
            return "0";
        }
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

    /**
     * 转换地址（例如格式：广东省-河源市-源城区）
     *
     * @param province 省份
     * @param city    城市
     * @param area    区、县、镇
     * @return {@link String}
     */
    public static String converAddress(String province,String city,String area){

        StringBuilder addressBuilder = new StringBuilder();
        if(!StringUtils.isBlank(province)){
            addressBuilder.append(province).append("-");
            if(!StringUtils.isBlank(city)){
                addressBuilder.append(city).append("-");
                if(!StringUtils.isBlank(area)){
                    addressBuilder.append(area).append("-");
                }
            }
        }
        //防止addressBuilder为空造成异常
        String address = addressBuilder.toString();
        if(!StringUtils.isBlank(address)){
            address=address.substring(0,address.length()-1);
            return address;
        }
        //如果addressBuilder为空
        return "";
    }

    /**
     * 将address解析地址成一个Map
     * <p>
     *
     *
     * @param address 地址格式为：省-市-区
     * @return {@link Map}<{@link String},{@link String}>
     */
    public static Map<String,String> parseAddress(String address){

        try {
            String[] addressArray = address.split("-");
            String province = addressArray[0];
            String city = addressArray[1];
            String area = addressArray[2];
            return Map.of(PROVINCE_KEY,province,CITY_KEY,city,AREA_KEY,area);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("parseAddress error");
        }

    }

    public static void main(String[] args) {

//        //converCount测试示例：
//        String conver1 = ConverUtil.converCount(1234L);
//        String conver2 = ConverUtil.converCount(233300L);
//        String conver3 = ConverUtil.converCount(351234000L);
//
//        //1234
//        log.info(conver1);
//        //23.3万
//        log.info(conver2);
//        //3.5亿
//        log.info(conver3);

        //converAddress测试示例：
        String provice="广东省";
        String city="河源市";
        String area = "源城区";
//        String provice="";
//        String city="";
//        String area = "";

        String address = ConverUtil.converAddress(provice, city , area);

        Map<String, String> parseAddressMap = ConverUtil.parseAddress(address);
        String province2 = parseAddressMap.get(ConverUtil.PROVINCE_KEY);
        String city2 = parseAddressMap.get(ConverUtil.CITY_KEY);
        String area2 = parseAddressMap.get(ConverUtil.AREA_KEY);

        log.info(address);
        log.info(province2);
        log.info(city2);
        log.info(area2);

    }

}
