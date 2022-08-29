package org.starrism.mall.admin.core.domain.converter;

import org.springframework.stereotype.Component;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.common.domain.BaseConverters;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.admin.api.domain.vo.BmsUserVo;
import org.starrism.mall.common.support.Convertible;
import org.starrism.mall.data.domain.vo.CoreUser;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/14
 **/
@Component
public class BmsUserConverters implements BaseConverters {

    /**
     * <p>BmsUser 转 BmsUserVo 转换器</p>
     *
     * @param bmsUser 用户实体
     * @return org.starrism.mall.common.support.Convertible<org.starrism.mall.admin.core.domain.entity.BmsUser, org.starrism.mall.admin.api.domain.vo.BmsUserVo>
     * @author hedwing
     * @since 2022/8/14
     */
    Convertible<BmsUser, BmsUserVo> userEntityToUserVoConverters(BmsUser bmsUser) {
        return (source) -> Builder.of(BmsUserVo::new)
                .with(BmsUserVo::setId, bmsUser.getId())
                .with(BmsUserVo::setUsername, bmsUser.getUsername())
                .with(BmsUserVo::setNickname, bmsUser.getNickname())
                .with(BmsUserVo::setEmail, bmsUser.getEmail())
                .with(BmsUserVo::setPhoneNumber, bmsUser.getPhoneNumber())
                .with(BmsUserVo::setAvatarUrl, bmsUser.getAvatarUrl())
                .with(BmsUserVo::setSex, bmsUser.getSex())
                .build();
    }

    Convertible<BmsUser, CoreUser> toCoreUserConverters(BmsUser bmsUser) {
        return (source) -> Builder.of(CoreUser::new)
                .with(CoreUser::setId, bmsUser.getId())
                .with(CoreUser::setUsername, bmsUser.getUsername())
                .with(CoreUser::setNickname, bmsUser.getNickname())
                .with(CoreUser::setEmail, bmsUser.getEmail())
                .with(CoreUser::setPhoneNumber, bmsUser.getPhoneNumber())
                .with(CoreUser::setAvatarUrl, bmsUser.getAvatarUrl())
                .with(CoreUser::setPassword, bmsUser.getPassword())
                .with(CoreUser::setSex, bmsUser.getSex())
                .build();
    }

    public BmsUserVo userEntityToUserVo(BmsUser bmsUser) {
        return userEntityToUserVoConverters(bmsUser).convert(bmsUser);
    }

    public CoreUser toCoreUser(BmsUser bmsUser) {
        return toCoreUserConverters(bmsUser).convert(bmsUser);
    }
}
