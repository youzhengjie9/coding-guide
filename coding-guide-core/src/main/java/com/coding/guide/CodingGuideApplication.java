package com.coding.guide;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringBoot主启动类
 *
 * @author youzhengjie
 * @date 2022/11/05 17:18:55
 */
@SpringBootApplication
@EnableKnife4j //开启knife4j美化
public class CodingGuideApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(CodingGuideApplication.class, args);

    }

}
