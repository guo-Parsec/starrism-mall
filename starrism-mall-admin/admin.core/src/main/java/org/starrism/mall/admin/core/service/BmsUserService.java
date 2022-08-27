package org.starrism.mall.admin.core.service;

import org.starrism.mall.admin.api.domain.vo.AuthUser;

/**
 * <p>系统用户业务接口</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public interface BmsUserService {
    /**
     * <p>根据用户名查询用户信息</p>
     *
     * @param username 用户名
     * @return org.starrism.mall.admin.api.domain.vo.AuthUser
     * @author hedwing
     * @since 2022/8/27
     */
    AuthUser findUserByUsername(String username);
}
