package org.starrism.mall.common.pools;

/**
 * <p>权限相关常量定义池</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
public interface AuthPool {
    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "bms_user";

}
