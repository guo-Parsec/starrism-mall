package org.starrism.mall.admin.core.service;

import org.starrism.mall.admin.api.domain.vo.BmsParamVo;

/**
 * <p>基础参数接口</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
public interface BmsParamService {
    /**
     * <p>根据参数码获取参数</p>
     *
     * @param paramCode 参数码
     * @return org.starrism.mall.admin.api.domain.vo.BmsParamVo
     * @author hedwing
     * @since 2022/8/29
     */
    BmsParamVo findByParamCode(String paramCode);
}
