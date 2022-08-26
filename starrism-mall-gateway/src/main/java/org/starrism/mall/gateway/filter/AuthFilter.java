package org.starrism.mall.gateway.filter;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.starrism.mall.common.pools.AuthPool;
import org.starrism.mall.common.rest.ResultCode;
import org.starrism.mall.common.service.RedisService;
import org.starrism.mall.common.util.ServletUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.common.util.TokenUtil;
import org.starrism.mall.common.util.WebfluxServletUtil;
import org.starrism.mall.gateway.properties.IgnoreUrlProperties;
import reactor.core.publisher.Mono;

/**
 * <p>认证过滤器</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    final IgnoreUrlProperties ignoreUrlProperties;

    final RedisService redisService;

    public AuthFilter(IgnoreUrlProperties ignoreUrlProperties, RedisService redisService) {
        this.ignoreUrlProperties = ignoreUrlProperties;
        this.redisService = redisService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String urlPath = request.getURI().getPath();
        // 判断当前请求url是否为白名单过滤url
        if (StrUtil.matches(urlPath, ignoreUrlProperties.getUrls())) {
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StrUtil.isEmpty(token)) {
            return unauthorizedResponse(exchange, "令牌为空");
        }
        Claims claims = TokenUtil.parseToken(token);
        if (claims == null) {
            return unauthorizedResponse(exchange, "令牌已过期或令牌验证失败");
        }
        String userKey = TokenUtil.getUserKey(claims);
        Boolean authSuccess = redisService.hasKey(userKey);
        if (!authSuccess) {
            return unauthorizedResponse(exchange, "登录状态已过期");
        }
        ServerHttpRequest.Builder mutate = request.mutate();
        // 设置用户信息到请求
        addHeader(mutate, AuthPool.USER_TOKEN_KEY, userKey);
        addHeader(mutate, AuthPool.USER_ID, TokenUtil.getUserId(claims));
        addHeader(mutate, AuthPool.USER_NAME, TokenUtil.getUserName(claims));
        removeHeader(mutate, AuthPool.FROM_SOURCE);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    /**
     * <p>网关鉴权失败</p>
     *
     * @param exchange HTTP 请求-响应交互上下文
     * @param msg      错误信息
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author hedwing
     * @since 2022/8/27
     */
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        log.error("网关鉴权失败, 鉴权请求为 -> {}, 鉴权失败原因 -> {}", exchange.getRequest().getPath(), msg);
        return WebfluxServletUtil.write(exchange.getResponse(), msg, ResultCode.UNAUTHORIZED);
    }

    private String getTokenKey(String userKey) {
        return null;
    }

    /**
     * 从请求信息中获取token
     *
     * @param request 请求
     * @return token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(AuthPool.JWT_TOKEN_HEADER);
        if (StrUtil.isNotBlank(token) && token.startsWith(AuthPool.JWT_TOKEN_PREFIX)) {
            return token.replaceFirst(AuthPool.JWT_TOKEN_PREFIX, StrUtil.EMPTY);
        }
        return null;
    }

    /**
     * 添加请求头
     *
     * @param mutate mutate
     * @param name   请求头名称
     * @param value  请求头参数
     */
    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null || StrUtil.isBlank(name)) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtil.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    /**
     * 内部请求来源参数清除
     *
     * @param mutate mutate
     * @param name   请求头名称
     */
    private void removeHeader(ServerHttpRequest.Builder mutate, final String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
