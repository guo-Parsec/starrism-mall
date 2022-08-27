package org.starrism.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;


/**
 * <p>网关swagger配置</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Configuration
public class GatewaySwaggerConfig {
    @Bean
    public SecurityConfiguration securityConfiguration(){
        return SecurityConfigurationBuilder.builder().build();
    }

    @Bean
    public UiConfiguration uiConfiguration(){
        return UiConfigurationBuilder.builder().build();
    }
}
