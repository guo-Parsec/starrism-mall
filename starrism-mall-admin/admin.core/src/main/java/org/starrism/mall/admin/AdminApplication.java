package org.starrism.mall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>应用启动入口</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@ComponentScan({"org.starrism.mall"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
