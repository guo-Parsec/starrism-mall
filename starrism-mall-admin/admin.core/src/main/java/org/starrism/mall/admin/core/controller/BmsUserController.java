package org.starrism.mall.admin.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.starrism.mall.admin.api.domain.vo.BmsUserVo;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;
import org.starrism.mall.admin.core.service.BmsDictService;
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.data.domain.vo.DictVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private BmsDictService dictService;

    @Autowired
    public void setDictRepository(BmsDictService dictService) {
        this.dictService = dictService;
    }

    @GetMapping(value = "/findById")
    public CommonResult<List<BmsUserVo>> addUser(@RequestParam Long id) {
        BmsUser user = bmsUserMapper.findById(id);
        return CommonResult.success(Stream.of(user.convert()).collect(Collectors.toList()));
    }

    @GetMapping(value = "/find")
    public CommonResult<List<DictVo>> findDictByCode(@RequestParam String code) {
        return CommonResult.success(dictService.findDictByCategoryCode(code));
    }
}
