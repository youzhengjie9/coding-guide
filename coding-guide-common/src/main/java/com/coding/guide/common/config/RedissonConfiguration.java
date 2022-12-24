package com.coding.guide.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置
 *
 * @author youzhengjie
 * @date 2022/12/24 16:33:05
 */
@Configuration
public class RedissonConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedissonClient getRedisson(){
        Config config = new Config();
        //单机配置:redis没有密码用这个（我们的redis没有密码所以用这个）
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        //单机配置:redis有密码用这个
//        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);

        //主从配置用这个
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});

        return Redisson.create(config);
    }

}