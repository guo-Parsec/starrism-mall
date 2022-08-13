package org.starrism.mall.admin.core.controller;

import org.springframework.web.bind.annotation.*;
import org.starrism.mall.admin.api.BmsUser;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;
import org.starrism.mall.common.rest.CommonResult;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@RestController
@RequestMapping(value = "/v1/bms/user")
public class BmsUserController {
    @Resource
    private BmsUserMapper bmsUserMapper;

    @GetMapping(value = "/add", produces = "application/json")
    public CommonResult<Void> addUser(@RequestParam String username) {
        BmsUser bmsUser = new BmsUser();
        bmsUser.setUsername(username);
        bmsUser.setPassword("123456");
        bmsUser.setNickname("1");
        bmsUser.setEmail("111");
        bmsUser.setPhoneNumber("111");
        bmsUserMapper.addUser(bmsUser);
        return CommonResult.success();
    }
}
