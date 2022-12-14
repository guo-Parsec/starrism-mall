package org.starrism.mall.base.domain.converter;


import org.starrism.mall.base.domain.entity.BmsParam;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.common.domain.BaseConverters;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.support.Convertible;

/**
 * <p>参数数据转换</p>
 *
 * @author hedwing
 * @since 2022/8/14
 **/
public class ParamConverters implements BaseConverters {

    /**
     * <p>BmsParam 转 BmsParamVo 转换器</p>
     *
     * @param bmsParam 参数实体
     * @author hedwing
     * @since 2022/8/14
     */
    static Convertible<BmsParam, BmsParamVo> toParamVoConverters(BmsParam bmsParam) {
        return (source) -> Builder.of(BmsParamVo::new)
                .with(BmsParamVo::setId, bmsParam.getId())
                .with(BmsParamVo::setParamCode, bmsParam.getParamCode())
                .with(BmsParamVo::setParamValue, bmsParam.getParamValue())
                .with(BmsParamVo::setParamName, bmsParam.getParamName())
                .with(BmsParamVo::setGroupCode, bmsParam.getGroupCode())
                .with(BmsParamVo::setDescription, bmsParam.getDescription())
                .build();
    }

    /**
     * <p>BmsParam 转 BmsParamVo</p>
     *
     * @param bmsParam bmsParam
     * @return org.starrism.mall.base.domain.vo.BmsParamVo
     * @author hedwing
     * @since 2022/8/31
     */
    public static BmsParamVo toParamVo(BmsParam bmsParam) {
        return toParamVoConverters(bmsParam).convert(bmsParam);
    }

}
