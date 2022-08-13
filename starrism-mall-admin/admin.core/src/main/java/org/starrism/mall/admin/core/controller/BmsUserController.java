package org.starrism.mall.admin.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starrism.mall.admin.api.BmsUser;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;

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

    @GetMapping(value = "/add")
    public void addUser() {
        BmsUser bmsUser = new BmsUser();
        bmsUser.setUsername("1");
        bmsUser.setPassword("123456");
        bmsUser.setNickname("1");
        bmsUser.setEmail("111");
        bmsUser.setPhoneNumber("111");
//        bmsUser.setAddName("admin");
//        bmsUser.setAddTime(LocalDateTime.now());
//        bmsUser.setModifyName("admin");
//        bmsUser.setModifyTime(LocalDateTime.now());
//        bmsUser.setEnableStatus(0);
        bmsUserMapper.addUser(bmsUser);
    }
}
