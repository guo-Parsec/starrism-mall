package org.starrism.mall.auth.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starrism.mall.admin.api.domain.dto.UserLoginDto;
import org.starrism.mall.auth.core.domain.vo.AuthInfoVo;
import org.starrism.mall.auth.core.service.AuthService;
import org.starrism.mall.common.rest.CommonResult;

/**
 * <p>认证Controller</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Api(value = "认证管理Web", tags = "认证管理接口")
@RestController
public class AuthController {
    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "用户登录", notes = "用户登录参数")
    @PostMapping(value = "/login")
    public CommonResult<AuthInfoVo> login(UserLoginDto dto) {
        return CommonResult.success(authService.login(dto));
    }
}
