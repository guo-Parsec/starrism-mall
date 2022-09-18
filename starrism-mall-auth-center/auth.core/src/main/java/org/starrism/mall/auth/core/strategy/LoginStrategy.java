package org.starrism.mall.auth.core.strategy;

import org.starrism.mall.admin.api.domain.dto.UserLoginDto;
import org.starrism.mall.auth.core.domain.vo.AuthInfoVo;
import org.starrism.mall.base.domain.vo.CoreUser;

/**
 * <p>登录策略器</p>
 *
 * @author hedwing
 * @since 2022/9/18
 **/
public interface LoginStrategy {
    /**
     * <p>登录人信息实体获取处理器</p>
     *
     * @param userLoginDto userLoginDto
     * @return CoreUser
     * @author hedwing
     * @since 2022/9/18
     */
    CoreUser loginPersonEntityAcquisitionProcessor(UserLoginDto userLoginDto);

    /**
     * <p>密码匹配错误处理器</p>
     *
     * @param coreUser coreUser
     * @author hedwing
     * @since 2022/9/18
     */
    void passwordMatchErrorProcessor(CoreUser coreUser);

    /**
     * <p>登录成功处理器</p>
     *
     * @param coreUser coreUser
     * @return org.starrism.mall.auth.core.domain.vo.AuthInfoVo
     * @author hedwing
     * @since 2022/9/18
     */
    AuthInfoVo loginSuccessProcessor(CoreUser coreUser);
}
