package org.starrism.mall.auth.core.service;

import org.starrism.mall.admin.api.domain.dto.UserLoginDto;
import org.starrism.mall.auth.core.domain.vo.AuthInfoVo;

/**
 * <p>认证服务接口</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public interface AuthService {
    /**
     * <p>用户登录接口</p>
     *
     * @param userLoginDto 用户登录参数
     * @return AuthInfoVo
     * @author hedwing
     * @since 2022/8/27
     */
    AuthInfoVo login(UserLoginDto userLoginDto);
}
