package com.coding.guide.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片的base64编码转MultipartFile的工具类
 *
 * @author youzhengjie
 * @date 2022/12/29 18:33:23
 */
@Slf4j
public class ImageBase64ToMultipartFileUtil {

    /**
     * 图片路径层级分隔符
     */
    private static final String SEPARATOR = "/";

    /**
     * 将图片的base64编码转成MultipartFile的方法
     *
     * @param imageBase64 图片的base64编码
     * @return {@link MultipartFile}
     */
    public static MultipartFile convert(String imageBase64) {
        //定义一个正则表达式的筛选规则，为了获取图片的类型
        String rgex = "data:image/(.*?);base64";
        if (StrUtil.isBlank(imageBase64)) {
            return null;
        }
        String type = getSubUtilSimple(imageBase64, rgex);
        //去除base64图片的前缀
        imageBase64 = imageBase64.replaceFirst("data:(.+?);base64,", "");
        byte[] imageByte;
        String fileName;
        //把图片转换成二进制
        imageByte = Base64.decode(imageBase64.replaceAll(" ", "+"));
        //随机生成图片的名字，同时根据类型结尾
        fileName = UUID.randomUUID() + "." + type;
        InputStream inputStream = new ByteArrayInputStream(imageByte);
        MockMultipartFile mockMultipartFile = null;
        try {
            mockMultipartFile = new MockMultipartFile(fileName, fileName, "", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("图片的base64编码转成MultipartFile失败");
        }
        return mockMultipartFile;
    }

    private static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }
}