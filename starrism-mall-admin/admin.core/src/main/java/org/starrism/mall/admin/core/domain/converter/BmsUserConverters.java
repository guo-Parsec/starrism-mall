package org.starrism.mall.admin.core.domain.converter;

import org.springframework.stereotype.Component;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.admin.api.domain.vo.BmsUserVo;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.base.domain.vo.CoreUser;
import org.starrism.mall.common.domain.BaseConverters;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.support.Convertible;

/**
 * <p>用户数据转换</p>
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
    static Convertible<BmsUser, BmsUserVo> userEntityToUserVoConverters(BmsUser bmsUser) {
        return (source) -> Builder.of(BmsUserVo::new)
                .with(BmsUserVo::setId, bmsUser.getId())
                .with(BmsUserVo::setUsername, bmsUser.getUsername())
                .with(BmsUserVo::setNickname, bmsUser.getNickname())
                .with(BmsUserVo::setEmail, bmsUser.getEmail())
                .with(BmsUserVo::setPhoneNumber, bmsUser.getPhoneNumber())
                .with(BmsUserVo::setAvatarUrl, bmsUser.getAvatarUrl())
                .with(BmsUserVo::setSex, bmsUser.getSex())
                .with(BmsUserVo::setEnableStatus, bmsUser.getEnableStatus())
                .build();
    }

    /**
     * <p>BmsUser 转 CoreUser 转换器</p>
     *
     * @param bmsUser 用户实体
     * @return org.starrism.mall.common.support.Convertible<org.starrism.mall.admin.core.domain.entity.BmsUser, org.starrism.mall.base.domain.vo.CoreUser>
     * @author hedwing
     * @since 2022/8/31
     */
    static Convertible<BmsUser, CoreUser> toCoreUserConverters(BmsUser bmsUser) {
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

    /**
     * <p>UserDto 转 BmsUser 转换器</p>
     *
     * @param userDto 用户传参
     * @return org.starrism.mall.common.support.Convertible<org.starrism.mall.admin.api.domain.dto.UserDto, org.starrism.mall.admin.core.domain.entity.BmsUser>
     * @author hedwing
     * @since 2022/8/31
     */
    static Convertible<UserDto, BmsUser> dtoToBmsUserConverters(UserDto userDto) {
        return (source) -> Builder.of(BmsUser::new)
                .with(BmsUser::setUsername, userDto.getUsername())
                .with(BmsUser::setPassword, userDto.getPassword())
                .with(BmsUser::setSex, userDto.getSex())
                .with(BmsUser::setNickname, userDto.getNickname())
                .with(BmsUser::setEmail, userDto.getEmail())
                .with(BmsUser::setPhoneNumber, userDto.getPhoneNumber())
                .with(BmsUser::setAvatarUrl, userDto.getAvatarUrl())
                .build();
    }

    /**
     * <p>BmsUser 转换为 BmsUserVo</p>
     *
     * @param bmsUser bmsUser
     * @return org.starrism.mall.admin.api.domain.vo.BmsUserVo
     * @author hedwing
     * @since 2022/8/31
     */
    public static BmsUserVo userEntityToUserVo(BmsUser bmsUser) {
        return userEntityToUserVoConverters(bmsUser).convert(bmsUser);
    }

    /**
     * <p>BmsUser 转换为 CoreUser</p>
     *
     * @param bmsUser bmsUser
     * @return org.starrism.mall.base.domain.vo.CoreUser
     * @author hedwing
     * @since 2022/8/31
     */
    public static CoreUser toCoreUser(BmsUser bmsUser) {
        return toCoreUserConverters(bmsUser).convert(bmsUser);
    }

    /**
     * <p>UserDto 转换为 BmsUser</p>
     *
     * @param userDto 用户参数
     * @return org.starrism.mall.admin.core.domain.entity.BmsUser
     * @author hedwing
     * @since 2022/8/31
     */
    public static BmsUser dtoToBmsUser(UserDto userDto) {
        return dtoToBmsUserConverters(userDto).convert(userDto);
    }
}
