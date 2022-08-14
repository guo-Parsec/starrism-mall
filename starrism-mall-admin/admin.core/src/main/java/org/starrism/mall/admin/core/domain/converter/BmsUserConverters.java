package org.starrism.mall.admin.core.domain.converter;

import org.springframework.stereotype.Component;
import org.starrism.mall.admin.api.domain.vo.BmsUserVo;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.common.annotation.DictConverter;
import org.starrism.mall.common.domain.BaseConverters;
import org.starrism.mall.common.domain.Dict;
import org.starrism.mall.common.support.Convertible;

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
    public Convertible<BmsUser, BmsUserVo> userEntityToUserVoConverters(BmsUser bmsUser) {
        return (source) -> {
            if (source == null) {
                return new BmsUserVo();
            }
            BmsUserVo bmsUserVo = new BmsUserVo();
            bmsUserVo.setId(bmsUser.getId());
            bmsUserVo.setUsername(bmsUser.getUsername());
            bmsUserVo.setNickname(bmsUser.getNickname());
            bmsUserVo.setEmail(bmsUser.getEmail());
            bmsUserVo.setPhoneNumber(bmsUser.getPhoneNumber());
            bmsUserVo.setAvatarUrl(bmsUser.getAvatarUrl());
            bmsUserVo.setSex(Dict.of(bmsUser.getSex()));
            return bmsUserVo;
        };
    }

    @DictConverter
    public BmsUserVo userEntityToUserVo(BmsUser bmsUser) {
        return userEntityToUserVoConverters(bmsUser).convert(bmsUser);
    }
}
