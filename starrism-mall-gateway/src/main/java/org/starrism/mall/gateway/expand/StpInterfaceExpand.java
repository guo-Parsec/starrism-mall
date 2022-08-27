package org.starrism.mall.gateway.expand;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;
import org.starrism.mall.common.domain.vo.AuthUser;
import org.starrism.mall.common.pools.AuthPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>自定义权限验证接口扩展</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Component
public class StpInterfaceExpand  implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        // 返回此 loginId 拥有的权限码列表
        AuthUser authUser = (AuthUser) StpUtil.getSession().get(AuthPool.USER_SESSION);
        Set<String> permissions = authUser.getPermissions();
        if (permissions == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(permissions);
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        // 返回此 loginId 拥有的角色码列表
        AuthUser authUser = (AuthUser) StpUtil.getSession().get(AuthPool.USER_SESSION);
        Set<String> roles = authUser.getRoles();
        if (roles == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(roles);
    }
}
