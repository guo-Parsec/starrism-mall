package org.starrism.mall.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.starrism.mall.common.pools.AuthPool;
import org.starrism.mall.common.util.StrUtil;
import reactor.core.publisher.Mono;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthPool.JWT_TOKEN_HEADER);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        //从token中解析用户信息并设置到Header中去
        String realToken = token.replace(AuthPool.JWT_TOKEN_PREFIX, "");
        ServerHttpRequest request = exchange.getRequest().mutate().header(AuthPool.USER_TOKEN_HEADER, realToken).build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
