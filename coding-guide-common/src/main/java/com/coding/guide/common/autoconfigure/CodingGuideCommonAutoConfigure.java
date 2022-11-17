package com.coding.guide.common.autoconfigure;

import com.coding.guide.common.config.*;
import com.coding.guide.common.utils.JwtUtil;
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
@Import({JwtUtil.class})
public class CodingGuideCommonAutoConfigure {

    @Bean
    public CorsConfiguration corsConfiguration(){
        return new CorsConfiguration();
    }

    @Bean
    public MybatisPlusConfiguration mybatisPlusConfiguration(){
        return new MybatisPlusConfiguration();
    }

    @Bean
    public RedisTemplateConfiguration redisTemplateConfiguration(){
        return new RedisTemplateConfiguration();
    }

    @Bean
    public RemoveDruidAdConfiguration removeDruidAdConfiguration(){
        return new RemoveDruidAdConfiguration();
    }

}
