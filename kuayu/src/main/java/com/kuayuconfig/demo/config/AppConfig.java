package com.kuayuconfig.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

//用这个类来代替xml配置
@Configuration
public class AppConfig {
    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool();
        return jedisPool;
    }
}
