package org.starrism.mall.admin.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starrism.mall.admin.api.domain.dto.BmsParamQueryDto;
import org.starrism.mall.admin.core.service.BmsParamService;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.common.pools.UrlPool;
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.data.domain.vo.PageData;

/**
 * <p>系统参数web</p>
 *
 * @author hedwing
 * @since 2022/9/25
 **/
@Api(value = "系统参数管理Web", tags = "系统参数管理接口")
@RestController
@RequestMapping(value = UrlPool.BMS_PARAM_PREFIX)
public class BmsParamController {
    private final BmsParamService bmsParamService;

    public BmsParamController(BmsParamService bmsParamService) {
        this.bmsParamService = bmsParamService;
    }

    @ApiOperation(value = "分页查询系统参数", notes = "查询参数")
    @GetMapping(value = {"/", "/page"})
    public CommonResult<PageData<BmsParamVo>> page(@Validated BmsParamQueryDto dto) {
        return CommonResult.success(bmsParamService.queryPage(dto));
    }
}
