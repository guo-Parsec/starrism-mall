package org.starrism.mall.auth.core.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.api.domain.dto.UserLoginDto;
import org.starrism.mall.admin.api.domain.vo.AuthUser;
import org.starrism.mall.admin.api.feign.BmsUserClient;
import org.starrism.mall.auth.core.domain.vo.AuthInfoVo;
import org.starrism.mall.auth.core.service.AuthService;
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.common.util.StrUtil;

import javax.annotation.Resource;

/**
 * <p>认证服务实现类</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    BmsUserClient bmsUserClient;

    /**
     * 登录认证
     *
     * @param userLoginDto 用户登录体
     * @return org.lime.hedwing.auth.pojo.vo.AuthInfoVo
     * @author guochengqiang
     * @date 2022/5/27 15:06
     * @modify
     */
    @Override
    public AuthInfoVo login(UserLoginDto userLoginDto) {
        SaTokenInfo saTokenInfo = null;
        if (userLoginDto == null || StrUtil.isBlank(userLoginDto.getUsername())) {
            throw new RuntimeException();
//            throw new AuthException("AuthRestStatus.USERNAME_OR_PASSWORD_ERROR");
        }
        CommonResult<AuthUser> clientApi = bmsUserClient.findUserByUsername(userLoginDto.getUsername());
//        AuthUser authUserVo = RestApi.getSuccessData(clientApi);
//        User userInfo = authUserVo.getUserInfo();
//        if (userInfo == null) {
//            throw new AuthException(AuthRestStatus.USERNAME_OR_PASSWORD_ERROR);
//        }
//        String password = userInfo.getPassword();
//        String slat = userInfo.getSalt();
//        if (!password.equals(SaSecureUtil.md5BySalt(userLoginDto.getPassword(), slat))) {
//            throw new AuthException(AuthRestStatus.USERNAME_OR_PASSWORD_ERROR);
//        }
//        // 密码校验成功后登录，一行代码实现登录
//        StpUtil.login(userInfo.getId());
//        // 将用户信息存储到Session中
//        StpUtil.getSession().set(AuthPool.USER_SESSION, authUserVo);
//        // 获取当前登录用户Token信息
//        saTokenInfo = StpUtil.getTokenInfo();
//        AccessTokenVo accessToken = AccessTokenVo.builder()
//                .accessToken(saTokenInfo.getTokenValue())
//                .accessTokenName(saTokenInfo.getTokenName())
//                .build();
//        return AuthInfoVo.builder()
//                .userVo(UserConvert.INSTANCE.entityToVo(userInfo))
//                .roles(authUserVo.getRoles())
//                .permissions(authUserVo.getPermissions())
//                .accessToken(accessToken)
//                .build();
        return null;
    }
}
