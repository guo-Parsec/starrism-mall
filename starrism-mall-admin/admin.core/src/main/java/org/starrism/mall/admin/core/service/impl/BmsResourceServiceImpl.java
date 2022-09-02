package org.starrism.mall.admin.core.service.impl;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.core.service.BmsResourceService;
import org.starrism.mall.admin.core.service.BmsRoleService;
import org.starrism.mall.base.repository.BmsResourceRepository;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
import org.starrism.mall.common.util.CollectionUtil;
import org.starrism.mall.common.util.StrUtil;

import javax.annotation.Resource;
import java.util.Set;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/9/2
 **/
@Service(value = "bmsResourceService")
public class BmsResourceServiceImpl implements BmsResourceService {
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(BmsResourceServiceImpl.class);

    private BmsRoleService roleService;
    @Resource
    private BmsResourceRepository resourceRepository;

    @Autowired
    public void setRoleService(BmsRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * <p>查询角色的资源列表</p>
     *
     * @param roleId roleId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/9/2
     */
    @Override
    public Set<String> findResourcesForRoleId(Long roleId) {
        return findResourcesForRoleIds(Sets.newHashSet(roleId));
    }

    /**
     * <p>查询角色列表的资源列表</p>
     *
     * @param roleIds roleIds
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/9/2
     */
    @Override
    public Set<String> findResourcesForRoleIds(Set<Long> roleIds) {
        if (CollectionUtil.isEmpty(roleIds)) {
            LOGGER.error("查询角色列表的资源列表时角色列表不能为空");
            return Sets.newHashSet();
        }
        Set<String> resourceCodeByRole = resourceRepository.findResourceCodeByRole(roleIds);
        if (CollectionUtil.isEmpty(resourceCodeByRole)) {
            LOGGER.warn("查询角色列表{}的资源列表为空", roleIds);
        }
        return resourceCodeByRole;
    }

    /**
     * <p>查询角色的资源列表</p>
     *
     * @param roleCode roleCode
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/9/2
     */
    @Override
    public Set<String> findResourcesForRoleCode(String roleCode) {
        if (StrUtil.isBlank(roleCode)) {
            LOGGER.warn("roleCode为空");
            return Sets.newHashSet();
        }
        return findResourcesForRoleCodes(Sets.newHashSet(roleCode));
    }

    /**
     * <p>查询角色列表的资源列表</p>
     *
     * @param roleCodes roleCodes
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/9/2
     */
    @Override
    public Set<String> findResourcesForRoleCodes(Set<String> roleCodes) {
        if (CollectionUtil.isEmpty(roleCodes)) {
            LOGGER.warn("roleCodes为空");
            return Sets.newHashSet();
        }
        Set<Long> roleIds = roleService.findRoleIdByRoleCodes(roleCodes);
        return findResourcesForRoleIds(roleIds);
    }
}
