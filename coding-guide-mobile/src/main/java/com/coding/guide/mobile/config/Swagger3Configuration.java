package com.coding.guide.mobile.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger3配置类
 * 解决springboot2.7之后使用swagger2会造成启动报错，故升级为swagger3
 * @author youzhengjie
 * @date 2022/11/05 17:20:51
 */
@Configuration
@EnableOpenApi
@EnableKnife4j //开启knife4j美化
public class Swagger3Configuration {

  @Bean
  public Docket createRestApi(){
    // 文档类型为swagger3
    return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            //是否开启 (true=开启,false=隐藏。生产环境建议设置为false)
            .enable(true)
            .select()
            //扫描的路径包
            .apis(RequestHandlerSelectors.basePackage("com.coding.guide.mobile.controller"))
            .paths(PathSelectors.any())
            .build();
  }

  private ApiInfo apiInfo(){
    return new ApiInfoBuilder()
            .title("coding-guide-mobile的接口文档")
            .description("coding-guide-mobile的接口文档")
            // 本API负责人的联系信息
            .contact(new Contact("youzhengjie",
                    "https://blog.csdn.net/weixin_50071998",
                    "1550324080@qq.com"))
            .version("1.0")
            .build();
  }

}
