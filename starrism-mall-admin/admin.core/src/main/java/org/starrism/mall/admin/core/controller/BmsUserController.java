package org.starrism.mall.admin.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.starrism.mall.admin.api.domain.entity.BmsUser;
import org.starrism.mall.admin.api.domain.vo.BmsDictVo;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;
import org.starrism.mall.admin.core.repository.BmsDictRepository;
import org.starrism.mall.common.rest.CommonResult;

import javax.annotation.Resource;
import java.util.List;

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

    private BmsDictRepository dictRepository;

    @Autowired
    public void setDictRepository(BmsDictRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

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

    @GetMapping(value = "/find")
    public CommonResult<List<BmsDictVo>> findDictByCode(@RequestParam String code) {
        return CommonResult.success(dictRepository.findDictByCategoryCode(code));
    }
}
