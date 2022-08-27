package org.starrism.mall.admin.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starrism.mall.admin.core.domain.entity.BmsMenu;
import org.starrism.mall.admin.core.service.BmsMenuService;
import org.starrism.mall.common.rest.CommonResult;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/21
 **/
@RestController
@RequestMapping(value = "/v1/bms/menu")
public class BmsMenuController {
    private final BmsMenuService bmsMenuService;

    public BmsMenuController(BmsMenuService bmsMenuService) {
        this.bmsMenuService = bmsMenuService;
    }

    @GetMapping(value = "/find/all")
    public CommonResult<List<BmsMenu>> findAll() {
        return CommonResult.success(bmsMenuService.findAll());
    }

    @GetMapping(value = "/list/all")
    public CommonResult<List<BmsMenu>> listAll() {
        return CommonResult.success(bmsMenuService.listAll());
    }
}
