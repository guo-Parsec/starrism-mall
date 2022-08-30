package org.starrism.mall.auth.core.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.admin.api.domain.dto.UserLoginDto;
import org.starrism.mall.admin.api.domain.dto.MemberRegisterDto;
import org.starrism.mall.admin.api.feign.BmsUserClient;
import org.starrism.mall.auth.core.domain.vo.AuthInfoVo;
import org.starrism.mall.auth.core.exception.AuthException;
import org.starrism.mall.auth.core.rest.AuthResultCode;
import org.starrism.mall.auth.core.service.AuthService;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.domain.vo.AccessTokenVo;
import org.starrism.mall.common.pools.AuthPool;
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.data.domain.vo.CoreUser;

import javax.annotation.Resource;

/**
 * <p>认证服务实现类</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Service("authService")
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Resource
    BmsUserClient bmsUserClient;

    /**
     * <p>用户登录接口</p>
     *
     * @param userLoginDto 用户登录参数
     * @return AuthInfoVo
     * @author hedwing
     * @since 2022/8/27
     */
    @Override
    public AuthInfoVo login(UserLoginDto userLoginDto) {
        SaTokenInfo saTokenInfo = null;
        if (userLoginDto == null || StrUtil.isBlank(userLoginDto.getUsername())) {
            log.error("username cannot be empty");
            throw new AuthException(AuthResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        String username = userLoginDto.getUsername();
        CommonResult<CoreUser> clientApi = bmsUserClient.findUserByUsername(username);
        CoreUser coreUser = CommonResult.getSuccessData(clientApi);
        if (coreUser == null) {
            log.error("cannot find user of username={}", username);
            throw new AuthException(AuthResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        String password = coreUser.getPassword();
        String salePwd = SaSecureUtil.md5BySalt(userLoginDto.getPassword(), username);
        if (!password.equals(salePwd)) {
            log.error("Password for user {} does not match", username);
            throw new AuthException(AuthResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        // 密码校验成功后登录，一行代码实现登录
        StpUtil.login(coreUser.getId());
        // 将用户信息存储到Session中
        StpUtil.getSession().set(AuthPool.USER_SESSION, coreUser);
        // 获取当前登录用户Token信息
        saTokenInfo = StpUtil.getTokenInfo();
        AccessTokenVo accessToken = Builder.of(AccessTokenVo::new)
                .with(AccessTokenVo::setAccessToken, saTokenInfo.getTokenValue())
                .with(AccessTokenVo::setAccessTokenName, saTokenInfo.getTokenName())
                .build();
        return Builder.of(AuthInfoVo::new)
                .with(AuthInfoVo::setCoreUser, coreUser)
                .with(AuthInfoVo::setAccessToken, accessToken)
                .build();
    }

    /**
     * <p>前端用户注册接口</p>
     *
     * @param dto 注册参数
     * @return boolean
     * @author hedwing
     * @since 2022/8/29
     */
    @Override
    public boolean memberRegister(MemberRegisterDto dto) {
        UserDto userDto = Builder.of(UserDto::new)
                .with(UserDto::setPassword, dto.getPassword())
                .with(UserDto::setUsername, dto.getUsername())
                .with(UserDto::setEmail, dto.getEmail())
                .build();
        CommonResult<Boolean> commonResult = bmsUserClient.saveUser(userDto);
        return CommonResult.getSuccessData(commonResult);
    }
}
