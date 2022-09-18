package org.starrism.mall.auth.core.factory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.starrism.mall.auth.core.pool.UserLoginPool;
import org.starrism.mall.auth.core.strategy.DefaultLoginStrategy;
import org.starrism.mall.auth.core.strategy.LoginStrategy;
import org.starrism.mall.common.util.SpringUtil;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>登录策略生成工厂类</p>
 *
 * @author hedwing
 * @since 2022/9/18
 **/
@Component
public class LoginStrategyFactory implements InitializingBean {
    private static ConcurrentHashMap<String, LoginStrategy> CONTAINER = null;
    @Resource(name = UserLoginPool.DEFAULT_LOGIN_STRATEGY_NAME)
    LoginStrategy defaultLoginStrategy;

    public LoginStrategy getLoginStrategy(String strategyName) {
        LoginStrategy loginStrategy = CONTAINER.get(strategyName);
        if (loginStrategy == null) {
            return SpringUtil.getBean(DefaultLoginStrategy.class);
        }
        return loginStrategy;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CONTAINER = new ConcurrentHashMap<>(8);
        CONTAINER.put(UserLoginPool.DEFAULT_LOGIN_STRATEGY_NAME, defaultLoginStrategy);
    }
}
