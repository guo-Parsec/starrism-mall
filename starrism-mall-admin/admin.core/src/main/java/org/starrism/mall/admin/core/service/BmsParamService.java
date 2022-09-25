package org.starrism.mall.admin.core.service;

import org.starrism.mall.admin.api.domain.dto.BmsParamQueryDto;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.data.domain.vo.PageData;

/**
 * <p>基础参数接口</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
public interface BmsParamService {
    /**
     * <p>分页查询系统参数</p>
     *
     * @param dto dto
     * @return org.starrism.mall.data.domain.vo.PageData<org.starrism.mall.base.domain.vo.BmsParamVo>
     * @author hedwing
     * @since 2022/9/25
     */
    PageData<BmsParamVo> queryPage(BmsParamQueryDto dto);
}
