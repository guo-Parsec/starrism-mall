package org.starrism.mall.base.access;

import org.starrism.mall.base.domain.vo.BmsParamVo;

import java.util.List;

/**
 * <p>基础参数接口</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
public interface ParamAccess {
    /**
     * <p>根据参数码获取参数</p>
     *
     * @param paramCode 参数码
     * @return org.starrism.mall.admin.api.domain.vo.BmsParamVo
     * @author hedwing
     * @since 2022/8/29
     */
    BmsParamVo findByParamCode(String paramCode);

    /**
     * <p>根据组别和参数码(可选)获取参数信息</p>
     *
     * @param groupCode 组别
     * @param paramCode 参数码(可选)
     * @return java.util.List<org.starrism.mall.base.domain.vo.BmsParamVo>
     * @author hedwing
     * @since 2022/9/17
     */
    List<BmsParamVo> findByGroupCode(String groupCode, String paramCode);
}

