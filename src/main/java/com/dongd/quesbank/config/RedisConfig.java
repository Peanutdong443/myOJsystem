package com.dongd.quesbank.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfig {

    @Bean
    public RedisTemplate redisTeplate(RedisConnectionFactory redisConnectionFactory){
        //创建RedisTemplate对象
        RedisTemplate<String,Object> redisTeplate=new RedisTemplate<>();
        //设置连接工厂
        redisTeplate.setConnectionFactory(redisConnectionFactory);

        //创建JSON序列化工具
        GenericJackson2JsonRedisSerializer serializer=new GenericJackson2JsonRedisSerializer();

        //设置key的序列化方式
        redisTeplate.setKeySerializer(RedisSerializer.string());
        redisTeplate.setHashKeySerializer(RedisSerializer.string());

        //设置value的序列化方式

        redisTeplate.setValueSerializer(serializer);
        redisTeplate.setHashValueSerializer(serializer);

        return redisTeplate;
    }

}
