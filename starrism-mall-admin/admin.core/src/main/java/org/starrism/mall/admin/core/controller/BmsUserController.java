package org.starrism.mall.admin.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.admin.core.service.BmsLockAccountService;
import org.starrism.mall.admin.core.service.BmsUserService;
import org.starrism.mall.base.domain.vo.CoreUser;
import org.starrism.mall.common.pools.UrlPool;
import org.starrism.mall.common.rest.CommonResult;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Api(value = "用户管理Web", tags = "用户管理接口")
@RestController
@Validated
@RequestMapping(value = UrlPool.BMS_USER_PREFIX)
public class BmsUserController {
    final BmsUserService bmsUserService;

    BmsLockAccountService bmsLockAccountService;

    public BmsUserController(BmsUserService bmsUserService) {
        this.bmsUserService = bmsUserService;
    }

    @Autowired
    public void setBmsLockAccountService(BmsLockAccountService bmsLockAccountService) {
        this.bmsLockAccountService = bmsLockAccountService;
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

    @ApiOperation(value = "锁定用户", notes = "用户")
    @PostMapping(value = "/lock")
    public CommonResult<Void> lockUser(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "scheduledUnlockTime") LocalDateTime scheduledUnlockTime,
                                       @RequestParam(value = "lockTime") LocalDateTime lockTime,
                                       @RequestParam(value = "lockReason") String lockReason) {
        bmsLockAccountService.lockUser(username, scheduledUnlockTime, lockTime, lockReason);
        return CommonResult.success();
    }
}
