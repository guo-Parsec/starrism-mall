package org.starrism.mall.admin.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.core.domain.converter.BmsUserConverters;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;
import org.starrism.mall.admin.core.service.BmsRoleService;
import org.starrism.mall.admin.core.service.BmsUserService;
import org.starrism.mall.data.domain.entity.BaseEntity;
import org.starrism.mall.data.domain.vo.CoreUser;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

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
    private BmsRoleService bmsRoleService;
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
}
