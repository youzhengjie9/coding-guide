package com.coding.guide.mobile.config;

import com.coding.guide.mobile.entity.Question;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * caffeine本地缓存配置
 *
 * @author youzhengjie
 * @date 2022/11/20 20:22:13
 */
@Configuration
public class CaffeineConfiguration {

    /**
     * 面试题的caffeine缓存（这里利用到Spring默认的bean是单例的这个性质，来集中存储缓存对象）
     *
     * @return Cache<面试题id,面试题对象>
     */
    @Bean
    public Cache<Long,Question> questionCache(){
        return Caffeine.newBuilder()
                //初始化容量
                .initialCapacity(1000)
                //最大容量
                .maximumSize(10000L)
                //过期时间30分钟（从放入缓存那一刻开始计算）
                .expireAfterWrite(30L, TimeUnit.MINUTES)
                //构建caffeine缓存
                .build();
    }


}
