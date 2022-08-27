package org.starrism.mall.admin.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starrism.mall.admin.api.domain.vo.AuthUser;
import org.starrism.mall.admin.core.service.BmsUserService;
import org.starrism.mall.common.rest.CommonResult;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@RestController
@RequestMapping(value = "/v1/bms/user")
public class BmsUserController {
    BmsUserService bmsUserService;
    @Autowired
    public void setBmsUserService(BmsUserService bmsUserService) {
        this.bmsUserService = bmsUserService;
    }

    @GetMapping(value = "/find/{username}")
    public CommonResult<AuthUser> findUserByUsername(@PathVariable String username) {
        return CommonResult.success(bmsUserService.findUserByUsername(username));
    }
}
