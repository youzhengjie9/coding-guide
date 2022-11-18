package com.coding.guide.common.autoconfigure;

import com.coding.guide.common.config.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * coding-guide自动配置类（通过META-INF指定扫描这个文件，则会把所有bean加载到Spring容器中）
 *
 * @author youzhengjie
 * @date 2022/11/17 11:50:02
 */
@Configuration
@EnableConfigurationProperties({JwtProperties.class})
@Import({UtilsConfiguration.class,RedisTemplateConfiguration.class})
@Slf4j(topic = "coding-guide-common-autoconfigure")
public class CodingGuideCommonAutoConfigure {

    @Bean
    public CorsConfiguration corsConfiguration(){
        log.info("load corsConfiguration bean success");
        return new CorsConfiguration();
    }

    @Bean
    public MybatisPlusConfiguration mybatisPlusConfiguration(){
        log.info("load mybatisPlusConfiguration bean success");
        return new MybatisPlusConfiguration();
    }

    @Bean
    public RemoveDruidAdConfiguration removeDruidAdConfiguration(){
        log.info("load removeDruidAdConfiguration bean success");
        return new RemoveDruidAdConfiguration();
    }

}
