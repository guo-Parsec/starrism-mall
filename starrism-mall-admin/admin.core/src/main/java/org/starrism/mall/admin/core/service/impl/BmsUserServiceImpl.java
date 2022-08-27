package org.starrism.mall.admin.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.core.domain.converter.BmsUserConverters;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;
import org.starrism.mall.admin.core.service.BmsUserService;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.domain.vo.AuthUser;
import org.starrism.mall.common.domain.vo.BmsUserVo;
import org.starrism.mall.data.domain.entity.BaseEntity;

import javax.annotation.Resource;

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
    BmsUserMapper bmsUserMapper;

    BmsUserConverters bmsUserConverters;

    @Autowired
    public void setBmsUserConverters(BmsUserConverters bmsUserConverters) {
        this.bmsUserConverters = bmsUserConverters;
    }

    /**
     * <p>根据用户名查询用户信息</p>
     *
     * @param username 用户名
     * @return org.starrism.mall.common.domain.vo.AuthUser
     * @author hedwing
     * @since 2022/8/27
     */
    @Override
    public AuthUser findUserByUsername(String username) {
        LambdaQueryWrapper<BmsUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BmsUser::getUsername, username);
        BmsUser bmsUser = bmsUserMapper.selectOne(wrapper);
        if (BaseEntity.isEmpty(bmsUser)) {
            log.info("cannot find bmsUser of username={}", username);
            return null;
        }
        BmsUserVo bmsUserVo = bmsUserConverters.userEntityToUserVo(bmsUser);
        return Builder.of(AuthUser::new)
                .with(AuthUser::setUserInfo, bmsUserVo)
                .with(AuthUser::setPassword, bmsUser.getPassword())
                .build();
    }
}
