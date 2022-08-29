package org.starrism.mall.admin.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.admin.core.service.BmsUserService;
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.data.domain.vo.CoreUser;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Api(value = "用户管理Web", tags = "用户管理接口")
@RestController
@Validated
@RequestMapping(value = "/v1/bms/user")
public class BmsUserController {
    BmsUserService bmsUserService;
    @Autowired
    public void setBmsUserService(BmsUserService bmsUserService) {
        this.bmsUserService = bmsUserService;
    }

    @ApiOperation(value = "根据用户名查询用户", notes = "用户名")
    @GetMapping(value = "/find/{username}")
    public CommonResult<CoreUser> findUserByUsername(@PathVariable String username) {
        return CommonResult.success(bmsUserService.findUserByUsername(username));
    }

    @ApiOperation(value = "创建用户", notes = "用户")
    @PostMapping(value = "/save")
    public CommonResult<Boolean> saveUser(@RequestBody @Validated UserDto userDto) {
        return CommonResult.success(bmsUserService.saveUser(userDto));
    }
}
