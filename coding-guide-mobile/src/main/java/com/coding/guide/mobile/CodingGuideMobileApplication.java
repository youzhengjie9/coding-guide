package com.coding.guide.mobile;

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
public class CodingGuideMobileApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(CodingGuideMobileApplication.class, args);

    }

}
