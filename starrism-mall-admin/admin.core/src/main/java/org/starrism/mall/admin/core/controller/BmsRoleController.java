package org.starrism.mall.admin.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.starrism.mall.admin.core.service.BmsRoleService;
import org.starrism.mall.common.pools.UrlPool;
import org.starrism.mall.common.rest.CommonResult;

import java.util.Set;

/**
 * <p>角色管理</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Api(value = "角色管理Web", tags = "角色管理接口")
@RestController
@RequestMapping(value = UrlPool.BMS_ROLE_PREFIX)
public class BmsRoleController {
    private BmsRoleService bmsRoleService;

    @Autowired
    public void setBmsRoleService(BmsRoleService bmsRoleService) {
        this.bmsRoleService = bmsRoleService;
    }

    @ApiOperation(value = "根据用户名查询角色", notes = "用户名")
    @GetMapping(value = "/find/role-code/by/username")
    public CommonResult<Set<String>> findRoleCodesByUsername(@RequestParam("username") String username) {
        return CommonResult.success(bmsRoleService.findRoleCodeListByUsername(username));
    }

    @ApiOperation(value = "根据用户id查询角色", notes = "用户id")
    @GetMapping(value = "/find/role-code/by/userId")
    public CommonResult<Set<String>> findRoleCodesByUserId(@RequestParam("userId") Long userId) {
        return CommonResult.success(bmsRoleService.findRoleCodeListByUserId(userId));
    }
}
