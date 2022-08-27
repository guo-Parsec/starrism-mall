package org.starrism.mall.data.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.starrism.mall.common.config.CommonRedisConfig;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@EnableCaching
@Configuration
public class RedisConfig extends CommonRedisConfig {

}
