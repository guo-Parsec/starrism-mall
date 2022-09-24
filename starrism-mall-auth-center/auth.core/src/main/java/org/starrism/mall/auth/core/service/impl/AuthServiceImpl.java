package org.starrism.mall.auth.core.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.api.domain.dto.MemberRegisterDto;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.admin.api.domain.dto.UserLoginDto;
import org.starrism.mall.admin.api.feign.BmsUserClient;
import org.starrism.mall.auth.core.domain.vo.AuthInfoVo;
import org.starrism.mall.auth.core.domain.vo.WrongPwdVo;
import org.starrism.mall.auth.core.exception.AuthException;
import org.starrism.mall.auth.core.factory.LoginStrategyFactory;
import org.starrism.mall.auth.core.pool.UserLoginPool;
import org.starrism.mall.auth.core.rest.AuthResultCode;
import org.starrism.mall.auth.core.service.AuthService;
import org.starrism.mall.auth.core.strategy.LoginStrategy;
import org.starrism.mall.base.access.ParamAccess;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.base.domain.vo.CoreUser;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
import org.starrism.mall.common.pools.ParamPool;
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.common.util.StrUtil;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * <p>认证服务实现类</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Service("authService")
public class AuthServiceImpl implements AuthService {
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(AuthServiceImpl.class);
    @Resource
    private BmsUserClient bmsUserClient;
    @Resource
    private ParamAccess paramAccess;

    private final LoginStrategyFactory loginStrategyFactory;

    public AuthServiceImpl(LoginStrategyFactory loginStrategyFactory) {
        this.loginStrategyFactory = loginStrategyFactory;
    }

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
        // 获取登录策略器
        LoginStrategy loginStrategy = loginStrategyFactory.getLoginStrategy(getLoginStrategyName());
        // 执行登录人信息实体获取处理器
        CoreUser coreUser = loginStrategy.loginPersonEntityAcquisitionProcessor(userLoginDto);
        String salePwd = SaSecureUtil.md5BySalt(userLoginDto.getPassword(), coreUser.getUsername());
        if (!coreUser.getPassword().equals(salePwd)) {
            WrongPwdVo wrongPwdVo = loginStrategy.passwordMatchErrorProcessor(coreUser);
            LOGGER.error("Password for user {} does not match", coreUser.getUsername());
            throw new AuthException(wrongPwdVo.infoMessage(), AuthResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        return loginStrategy.loginSuccessProcessor(coreUser);
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
    @GlobalTransactional(rollbackFor = Exception.class)
    public boolean memberRegister(MemberRegisterDto dto) {
        UserDto userDto = Builder.of(UserDto::new)
                .with(UserDto::setPassword, dto.getPassword())
                .with(UserDto::setUsername, dto.getUsername())
                .with(UserDto::setEmail, dto.getEmail())
                .build();
        CommonResult<Boolean> commonResult = bmsUserClient.saveUser(userDto);
        return CommonResult.getSuccessData(commonResult);
    }

    /**
     * <p>登出</p>
     *
     * @return boolean
     * @author hedwing
     * @since 2022/8/31
     */
    @Override
    public boolean logout(String userId) {
        LOGGER.debug("用户[userId={}]正在退出系统", userId);
        StpUtil.logout(userId);
        LOGGER.debug("用户[userId={}]退出系统成功", userId);
        return true;
    }

    /**
     * <p>获取系统中设置的登录策略名</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/9/18
     */
    private String getLoginStrategyName() {
        BmsParamVo param = paramAccess.findByParamCode(ParamPool.LOGIN_STRATEGY_KEY);
        if (param == null || StrUtil.isBlank(param.getParamValue())) {
            LOGGER.warn("系统中未指定登录策略器，将使用[{}]", UserLoginPool.DEFAULT_LOGIN_STRATEGY_NAME);
        }
        String strategyName = Optional.ofNullable(param).map(BmsParamVo::getParamValue).orElse(UserLoginPool.DEFAULT_LOGIN_STRATEGY_NAME);
        LOGGER.debug("当前系统使用的登录策略器为{}", strategyName);
        return strategyName;
    }
}
