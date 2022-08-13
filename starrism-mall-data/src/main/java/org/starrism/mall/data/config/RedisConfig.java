package org.starrism.mall.data.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@EnableCaching
@Configuration
public class RedisConfig {
    /**
     * 申明缓存管理器，会创建一个切面（aspect）并触发Spring缓存注解的切点（pointcut）
     * 根据类或者方法所使用的注解以及缓存的状态，这个切面会从缓存中获取数据，将数据添加到缓存之中或者从缓存中移除某个值
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer());
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                .computePrefixWith(name -> "starrism:mall:" + name + ":")
                .serializeValuesWith(pair)
                .entryTtl(Duration.ofMinutes(10));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(defaultCacheConfig).build();
    }

    @Bean(name = "selfKeyGenerator")
    public KeyGenerator selfKeyGenerator() {
        final String prefix = "self";
        final String sp = ":";
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix).append(sp);
            sb.append(target.getClass().getSimpleName())
                    .append(sp);
            sb.append(method.getName());
            for (Object param : params) {
                sb.append(sp);
                sb.append(param);
            }
            return sb.toString();
        };
    }

    /**
     * @param
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String, java.lang.Object>
     * @description 将redisTemplate注入容器
     * @author zhou
     * @create 2021/4/20 17:30
     **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        this.initRedisTemplate(redisTemplate, connectionFactory);
        return redisTemplate;
    }

    /**
     * @param
     * @return void
     * @description 初始化redis序列化方式, 不开启事务
     * @author zhou
     * @create 2021/4/20 17:29
     **/
    private void initRedisTemplate(RedisTemplate<String, Object> redisTemplate, LettuceConnectionFactory connectionFactory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        //redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(connectionFactory);
    }


}
