package org.starrism.mall.admin.core.service;

import java.util.Set;

/**
 * <p>资源类业务接口</p>
 *
 * @author hedwing
 * @since 2022/9/2
 **/
public interface BmsResourceService {
    /**
     * <p>查询角色的资源列表</p>
     *
     * @param roleId roleId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/9/2
     */
    Set<String> findResourcesForRoleId(Long roleId);

    /**
     * <p>查询角色列表的资源列表</p>
     *
     * @param roleIds roleIds
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/9/2
     */
    Set<String> findResourcesForRoleIds(Set<Long> roleIds);

    /**
     * <p>查询角色的资源列表</p>
     *
     * @param roleCode roleCode
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/9/2
     */
    Set<String> findResourcesForRoleCode(String roleCode);

    /**
     * <p>查询角色列表的资源列表</p>
     *
     * @param roleCodes roleCodes
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/9/2
     */
    Set<String> findResourcesForRoleCodes(Set<String> roleCodes);
}
