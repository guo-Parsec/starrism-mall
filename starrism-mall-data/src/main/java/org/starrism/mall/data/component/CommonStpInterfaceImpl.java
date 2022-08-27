package org.starrism.mall.data.component;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CommonStpInterfaceImpl implements StpInterface {
    private final Logger logger = LoggerFactory.getLogger(CommonStpInterfaceImpl.class);


    /**
     * 返回指定账号id所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        AuthUser authUser = (AuthUser) StpUtil.getSession().get(AuthPool.USER_SESSION);
        if (authUser != null) {
            Set<String> permissions = authUser.getPermissions();
            if (permissions == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(permissions);
        }
        return null;
    }

    /**
     * 返回指定账号id所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}