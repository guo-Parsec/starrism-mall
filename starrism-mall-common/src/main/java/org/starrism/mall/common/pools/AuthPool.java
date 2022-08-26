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
     * 令牌秘钥
     */
    String SECRET = "aGVkd2luZ3NlY3JldA==";

    /**
     * 用户id标识
     */
    String USER_ID = "LOGIN_USER_ID";

    /**
     * 用户标识
     */
    String USER_TOKEN_KEY = "LOGIN_USER_TOKEN";

    /**
     * 用户名称标识
     */
    String USER_NAME = "LOGIN_USERNAME";

    /**
     * 请求来源
     */
    String FROM_SOURCE = "from-source";

}
