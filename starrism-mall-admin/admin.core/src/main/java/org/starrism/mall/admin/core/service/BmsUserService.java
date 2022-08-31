package org.starrism.mall.admin.core.service;

import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.base.domain.vo.CoreUser;

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
     * @return org.starrism.mall.data.domain.vo.CoreUser
     * @author hedwing
     * @since 2022/8/27
     */
    CoreUser findUserByUsername(String username);

    /**
     * 保存用户
     *
     * @param userDto 用户
     * @return 是否保存成功
     */
    boolean saveUser(UserDto userDto);
}
