package org.starrism.mall.data.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.starrism.mall.data.interceptor.FieldFillingInterceptorPlugin;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Configuration
public class MyBatisConfig {
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return (configuration) -> configuration.addInterceptor(new FieldFillingInterceptorPlugin());
    }
}
