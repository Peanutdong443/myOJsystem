package com.dongd.quesbank.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfig {

    public RedisTemplate redisTeplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTeplate=new RedisTemplate();
        redisTeplate.setConnectionFactory(redisConnectionFactory);
        redisTeplate.setKeySerializer(new StringRedisSerializer());
        return redisTeplate;
    }

}
