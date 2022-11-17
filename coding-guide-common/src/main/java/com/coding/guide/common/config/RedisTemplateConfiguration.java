package com.coding.guide.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * SpringDataRedis配置类
 *
 * @author youzhengjie
 * @date 2022/11/08 17:54:11
 */
@Configuration
public class RedisTemplateConfiguration {

    /**
     * redistemplate<String,Object>的bean
     *
     * @param connectionFactory 连接工厂
     * @return {@link RedisTemplate}<{@link String}, {@link Object}>
     */
    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        // 创建RedisTemplate<String,Object>对象
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        // 设置连接工厂
        template.setConnectionFactory(connectionFactory);

        // fastjson2序列化工具
        FastJson2RedisSerializer serializer = new FastJson2RedisSerializer(Object.class);

        // 序列化String数据类型的key和value
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // 序列化Hash数据类型的key和value
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }



}
