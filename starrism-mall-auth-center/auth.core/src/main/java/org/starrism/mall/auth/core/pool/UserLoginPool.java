package org.starrism.mall.auth.core.pool;

/**
 * <p>用户登录相关操作常量池</p>
 *
 * @author hedwing
 * @since 2022/9/17
 **/
public interface UserLoginPool {
    /**
     * 用户登录密码尝试错误次数redis-key
     */
    String USER_LOGIN_PWD_WRONG_COUNT_REDIS_KEY = "starrism:auth:pwd:wrong:{userId}:count";

    /**
     * 用户登录密码尝试错误次数redis-key
     */
    String USER_LOGIN_PWD_WRONG_TIME_REDIS_KEY = "starrism:auth:pwd:wrong:{userId}:time";

    /**
     * 默认登录策略器名称
     */
    String DEFAULT_LOGIN_STRATEGY_NAME = "defaultLoginStrategy";

    /**
     * 用户登录密码尝试错误锁定原因
     */
    String USER_LOGIN_PWD_WRONG_LOCK_REASON = "登录时用户名密码错误在{time}小时内超过{count}次被系统自动锁定";

    /**
     * 用户自动解锁原因
     */
    String USER_AUTO_UNLOCK_REASON = "计划解锁时间[{scheduledUnlockTime}]已到,系统在用户再次登录时[{now}]自动解锁";
}
