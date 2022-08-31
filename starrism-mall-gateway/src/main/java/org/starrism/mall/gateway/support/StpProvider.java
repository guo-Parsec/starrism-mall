package org.starrism.mall.gateway.support;

import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import org.starrism.mall.base.domain.vo.CoreUser;
import org.starrism.mall.common.pools.AuthPool;
import org.starrism.mall.gateway.exceptions.NotClientException;

import java.util.List;

/**
 * <p>Sa-Token 权限认证扩展类</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public class StpProvider {
    /**
     * 检查客户端权限 否则抛出异常
     *
     * @param client 客户端权限
     */
    public static void checkClient(String client) {
        if (!hasClient(client)) {
            throw new NotClientException(client, StpUtil.getLoginType());
        }

    }

    /**
     * 是否拥有客户端权限
     *
     * @param client 客户端
     * @return true: 拥有 false: 未拥有
     */
    public static boolean hasClient(String client) {
        CoreUser coreUser = (CoreUser) StpUtil.getSession().get(AuthPool.USER_SESSION);
        if (coreUser == null) {
            return false;
        }
        List<String> clients = Lists.newArrayList("*");
        if (clients == null || clients.size() == 0) {
            return false;
        }
        return "*".equals(client) || clients.contains("*") || clients.contains(client);
    }
}
