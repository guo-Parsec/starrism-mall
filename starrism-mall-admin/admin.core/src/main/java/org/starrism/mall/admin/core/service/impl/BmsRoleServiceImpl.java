package org.starrism.mall.admin.core.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.api.domain.vo.BmsRoleVo;
import org.starrism.mall.admin.core.domain.converter.BmsRoleConverters;
import org.starrism.mall.admin.core.domain.entity.BmsRole;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.admin.core.mapper.BmsRoleMapper;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;
import org.starrism.mall.admin.core.service.BmsRoleService;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
import org.starrism.mall.common.util.CollectionUtil;
import org.starrism.mall.data.domain.entity.BaseDataEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>角色服务实现类</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Service("bmsRoleService")
public class BmsRoleServiceImpl implements BmsRoleService {
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(BmsRoleServiceImpl.class);
    @Resource
    private BmsRoleMapper bmsRoleMapper;
    @Resource
    private BmsUserMapper bmsUserMapper;

    /**
     * <p>根据用户名查询角色信息</p>
     *
     * @param username 用户名
     * @return java.util.List<org.starrism.mall.admin.api.domain.vo.BmsRoleVo>
     * @author hedwing
     * @since 2022/8/29
     */
    @Override
    public List<BmsRoleVo> findRolesByUsername(String username) {
        BmsUser user = bmsUserMapper.findByUsername(username);
        if (BaseDataEntity.isEmpty(user)) {
            LOGGER.debug("用户[username={}]的角色为空", username);
            return Lists.newArrayList();
        }
        return findRolesByUserId(user.getId());
    }

    /**
     * <p>根据用户id查询角色信息</p>
     *
     * @param userId 用户id
     * @return java.util.List<org.starrism.mall.admin.api.domain.vo.BmsRoleVo>
     * @author hedwing
     * @since 2022/8/29
     */
    @Override
    public List<BmsRoleVo> findRolesByUserId(Long userId) {
        List<BmsRole> roles = bmsRoleMapper.findByUser(userId);
        if (CollectionUtil.isEmpty(roles)) {
            LOGGER.debug("用户[userId={}]的角色为空", userId);
            return Lists.newArrayList();
        }
        return roles.stream().map(BmsRoleConverters::toRoleVo).collect(Collectors.toList());
    }

    /**
     * <p>根据用户名查询角色code</p>
     *
     * @param username 用户名
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/8/29
     */
    @Override
    public Set<String> findRoleCodeListByUsername(String username) {
        List<BmsRoleVo> roles = findRolesByUsername(username);
        return roles.stream().map(BmsRoleVo::getRoleCode).collect(Collectors.toSet());
    }

    /**
     * <p>根据用户id查询角色code</p>
     *
     * @param userId 用户id
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/8/29
     */
    @Override
    public Set<String> findRoleCodeListByUserId(Long userId) {
        List<BmsRoleVo> roles = findRolesByUserId(userId);
        return roles.stream().map(BmsRoleVo::getRoleCode).collect(Collectors.toSet());
    }

    /**
     * <p>根据roleCodes查询roleId列表</p>
     *
     * @param roleCodes roleCodes
     * @return java.util.Set<java.lang.Long>
     * @author hedwing
     * @since 2022/9/2
     */
    @Override
    public Set<Long> findRoleIdByRoleCodes(Set<String> roleCodes) {
        if (CollectionUtil.isEmpty(roleCodes)) {
            LOGGER.error("根据roleCodes查询role列表时roleCodes不能为空");
            return Sets.newHashSet();
        }
        List<BmsRole> roles = bmsRoleMapper.findByRoleCode(roleCodes);
        if (CollectionUtil.isEmpty(roles)) {
            LOGGER.warn("根据roleCodes{}查询role列表为空", roleCodes);
            return Sets.newHashSet();
        }
        return roles.stream().map(BmsRole::getId).collect(Collectors.toSet());
    }

    /**
     * <p>根据roleCodes查询role列表</p>
     *
     * @param roleCodes roleCodes
     * @return java.util.Set<java.lang.Long>
     * @author hedwing
     * @since 2022/9/2
     */
    @Override
    public List<BmsRoleVo> findRoleByRoleCodes(Set<String> roleCodes) {
        if (CollectionUtil.isEmpty(roleCodes)) {
            LOGGER.error("根据roleCodes查询role列表时roleCodes不能为空");
            return Lists.newArrayList();
        }
        List<BmsRole> role = bmsRoleMapper.findByRoleCode(roleCodes);
        if (CollectionUtil.isEmpty(role)) {
            LOGGER.warn("根据roleCodes{}查询role列表为空", roleCodes);
            return Lists.newArrayList();
        }
        return role.stream().map(BmsRoleConverters::toRoleVo).collect(Collectors.toList());
    }
}
