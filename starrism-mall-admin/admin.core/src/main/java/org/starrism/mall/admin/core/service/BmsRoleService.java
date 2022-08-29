package org.starrism.mall.admin.core.service;

import org.starrism.mall.admin.api.domain.vo.BmsRoleVo;

import java.util.List;
import java.util.Set;

/**
 * <p>角色服务类</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
public interface BmsRoleService {
    /**
     * <p>根据用户名查询角色信息</p>
     *
     * @param username 用户名
     * @return java.util.List<org.starrism.mall.admin.api.domain.vo.BmsRoleVo>
     * @author hedwing
     * @since 2022/8/29
     */
    List<BmsRoleVo> findRolesByUsername(String username);

    /**
     * <p>根据用户id查询角色信息</p>
     *
     * @param userId 用户id
     * @return java.util.List<org.starrism.mall.admin.api.domain.vo.BmsRoleVo>
     * @author hedwing
     * @since 2022/8/29
     */
    List<BmsRoleVo> findRolesByUserId(Long userId);

    /**
     * <p>根据用户名查询角色code</p>
     *
     * @param username 用户名
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/8/29
     */
    Set<String> findRoleCodeListByUsername(String username);

    /**
     * <p>根据用户id查询角色code</p>
     *
     * @param userId 用户id
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/8/29
     */
    Set<String> findRoleCodeListByUserId(Long userId);
}
