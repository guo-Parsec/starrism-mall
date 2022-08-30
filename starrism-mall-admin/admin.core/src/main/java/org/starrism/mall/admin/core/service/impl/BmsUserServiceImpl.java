package org.starrism.mall.admin.core.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.admin.core.domain.converter.BmsUserConverters;
import org.starrism.mall.admin.core.domain.entity.BmsRole;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.admin.core.mapper.BmsRoleMapper;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;
import org.starrism.mall.admin.core.rest.AdminResultCode;
import org.starrism.mall.admin.core.service.BmsRoleService;
import org.starrism.mall.admin.core.service.BmsUserService;
import org.starrism.mall.base.access.BmsParamAccess;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.exceptions.StarrismException;
import org.starrism.mall.common.pools.ParamPool;
import org.starrism.mall.common.util.CollectionUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.data.domain.entity.BaseDataEntity;
import org.starrism.mall.data.domain.entity.BaseEntity;
import org.starrism.mall.data.domain.vo.CoreUser;
import org.starrism.mall.data.pool.BasePool;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>系统用户业务实现类</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Service("bmsUserService")
public class BmsUserServiceImpl implements BmsUserService {
    private static final Logger log = LoggerFactory.getLogger(BmsUserServiceImpl.class);

    @Resource
    private BmsUserMapper bmsUserMapper;
    @Resource
    private BmsParamAccess bmsParamAccess;
    @Resource
    private BmsRoleMapper bmsRoleMapper;
    private final BmsRoleService bmsRoleService;
    private BmsUserConverters bmsUserConverters;

    @Autowired
    public BmsUserServiceImpl(BmsRoleService bmsRoleService) {
        this.bmsRoleService = bmsRoleService;
    }

    @Autowired
    public void setBmsUserConverters(BmsUserConverters bmsUserConverters) {
        this.bmsUserConverters = bmsUserConverters;
    }

    /**
     * <p>根据用户名查询用户信息</p>
     *
     * @param username 用户名
     * @return org.starrism.mall.data.domain.vo.CoreUser
     * @author hedwing
     * @since 2022/8/27
     */
    @Override
    public CoreUser findUserByUsername(String username) {
        LambdaQueryWrapper<BmsUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BmsUser::getUsername, username);
        BmsUser bmsUser = bmsUserMapper.selectOne(wrapper);
        if (BaseEntity.isEmpty(bmsUser)) {
            log.info("cannot find bmsUser of username={}", username);
            return null;
        }
        CoreUser coreUser = bmsUserConverters.toCoreUser(bmsUser);
        Set<String> roles = bmsRoleService.findRoleCodeListByUsername(username);
        coreUser.setRoles(Optional.ofNullable(roles).orElse(Sets.newHashSet()));
        return coreUser;
    }

    /**
     * 保存用户
     *
     * @param userDto 用户
     * @return 是否保存成功
     */
    @Override
    public boolean saveUser(UserDto userDto) {
        if (BaseEntity.isEmpty(userDto)) {
            if (log.isDebugEnabled()) {
                log.debug("save user[{}] to add", userDto);
            }
            return addUser(userDto);
        }
        if (log.isDebugEnabled()) {
            log.debug("save user[{}] to edit", userDto);
        }
        return editUser(userDto);
    }

    /**
     * <p>创建用户</p>
     *
     * @param userDto 用户参数
     * @return boolean
     * @author hedwing
     * @since 2022/8/29
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(UserDto userDto) {
        UserDto clone = userDto.clone();
        String username = userDto.getUsername();
        BmsUser existUser = bmsUserMapper.findByUsername(username);
        if (BaseDataEntity.isNotEmpty(existUser)) {
            log.error("用户名{}已存在", username);
            throw new StarrismException(AdminResultCode.USER_EXIST);
        }
        Set<String> roles = userDto.getRoleSet();
        if (CollectionUtil.isEmpty(roles)) {
            BmsParamVo defaultRoleParam = bmsParamAccess.findByParamCode(ParamPool.DEFAULT_ROLE_KEY);
            if (defaultRoleParam == null || StrUtil.isBlank(defaultRoleParam.getParamValue())) {
                log.error("默认角色参数不存在");
                return false;
            }
            roles = Sets.newHashSet(defaultRoleParam.getParamValue().split(BasePool.DEFAULT_DELIMITER));
        }
        clone.setPassword(SaSecureUtil.md5BySalt(userDto.getPassword(), username));
        BmsUser user = bmsUserConverters.dtoToBmsUser(clone);
        if (!setUserSex(user)) {
            return false;
        }
        user.setAddName(user.getUsername());
        user.setModifyName(user.getUsername());
        user.setEnableStatus(BasePool.ENABLE);
        bmsUserMapper.addUser(user);
        return grantRoleToUser(user.getId(), roles);
    }

    /**
     * <p>设置用户性别</p>
     *
     * @param bmsUser 用户信息
     * @return boolean
     * @author hedwing
     * @since 2022/8/30
     */
    private boolean setUserSex(BmsUser bmsUser) {
        if (bmsUser.getSex() == null) {
            try {
                BmsParamVo defaultSexParam = bmsParamAccess.findByParamCode(ParamPool.DEFAULT_SEX_KEY);
                if (defaultSexParam == null || StrUtil.isBlank(defaultSexParam.getParamValue())) {
                    log.warn("默认性别参数不存在, 统一赋值为0");
                    defaultSexParam = Builder.of(BmsParamVo::new)
                            .with(BmsParamVo::setParamValue, "0")
                            .build();
                }
                bmsUser.setSex(Integer.valueOf(defaultSexParam.getParamValue()));
                return true;
            } catch (NumberFormatException e) {
                log.error("设置默认性别码失败，原因为 -> ", e);
                return false;
            }
        }
        return true;
    }

    private boolean editUser(UserDto userDto) {
        return true;
    }

    public boolean grantRoleToUser(Long userId, Set<String> roleCodeSet) {
        LambdaQueryWrapper<BmsRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(BmsRole::getRoleCode, roleCodeSet);
        List<Long> roleIds = bmsRoleMapper.selectList(wrapper).stream()
                .map(BmsRole::getId)
                .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(roleIds)) {
            log.error("赋予用户[userId={}]的角色列表{}不存在", userId, roleCodeSet);
            return false;
        }
        bmsUserMapper.grantRoleToUser(userId, roleIds);
        return true;
    }
}
