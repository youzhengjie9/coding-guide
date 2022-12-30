package com.coding.guide.common.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成随机码工具类（验证码）
 *
 * @author youzhengjie
 * @date 2022/12/30 18:31:21
 */
public class GenerateCodeUtil {

    /**
     * 生成数字验证码
     *
     * @param length 验证码长度
     * @return {@link String}
     */
    public static String generateNumberCode(int length){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            //在高并发下ThreadLocalRandom的性能远远大于Random的性能
            int number = ThreadLocalRandom.current().nextInt(10);
            stringBuffer.append(number);
        }
        return stringBuffer.toString();
    }


}
