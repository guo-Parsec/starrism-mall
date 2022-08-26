package org.starrism.mall.gateway.filter;

import cn.dev33.satoken.id.SaIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p>全局过滤器，为请求添加 Id-Token</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public class ForwardAuthFilter implements GlobalFilter {
    private final Logger log = LoggerFactory.getLogger(ForwardAuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest newRequest = exchange
                .getRequest()
                .mutate()
                .header(SaIdUtil.ID_TOKEN, SaIdUtil.getToken())
                //.header(StpUtil.getTokenName(), StpUtil.getTokenValue())
                .build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        return chain.filter(newExchange);
    }
}
