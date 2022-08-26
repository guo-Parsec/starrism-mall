package org.starrism.mall.gateway.config;

import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.starrism.mall.gateway.properties.IgnoreUrlProperties;
import org.starrism.mall.gateway.support.StpProvider;

/**
 * <p>[Sa-Token 权限认证] 配置类</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Configuration
public class SaTokenConfig {
    final IgnoreUrlProperties ignoreUrlProperties;

    public SaTokenConfig(IgnoreUrlProperties ignoreUrlProperties) {
        this.ignoreUrlProperties = ignoreUrlProperties;
    }

    /**
     * 注册 Sa-Token全局过滤器
     *
     * @return cn.dev33.satoken.reactor.filter.SaReactorFilter
     * @author guochengqiang
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 开放地址
                .addExclude(ignoreUrlProperties.getUrls().toArray(new String[0]))
                // 鉴权方法：每次访问进入
                .setAuth(authObject -> {
                    // 登录校验 -- 拦截所有路由，并排除 /auth/login 用于开放登录
                    SaRouter.match("/**", "/auth/login", StpUtil::checkLogin);
                    // 权限认证 -- 不同模块, 校验不同权限
                    SaRouter.match("/admin/**", () -> StpProvider.checkClient("system"));
                })
                // 异常处理方法：每次setAuth函数出现异常时进入
                .setError(e -> {
                    e.printStackTrace();
                    ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return SaResult.error(e.getMessage());
                });
    }
}
