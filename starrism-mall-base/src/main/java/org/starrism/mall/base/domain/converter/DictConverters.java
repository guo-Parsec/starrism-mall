package org.starrism.mall.base.domain.converter;

import org.starrism.mall.base.domain.entity.BmsDictDetail;
import org.starrism.mall.base.domain.vo.DictVo;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.support.Convertible;

/**
 * <p>字典转换</p>
 *
 * @author hedwing
 * @since 2022/8/14
 **/
public class DictConverters {

    /**
     * <p>BmsDictDetail 转换为 DictVo 转换器</p>
     *
     * @param detail detail
     * @return org.starrism.mall.common.support.Convertible<org.starrism.mall.base.domain.entity.BmsDictDetail, org.starrism.mall.base.domain.vo.DictVo>
     * @author hedwing
     * @since 2022/8/31
     */
    static Convertible<BmsDictDetail, DictVo> dictDetailToVoConverter(BmsDictDetail detail) {
        return (source) -> Builder.of(DictVo::new)
                .with(DictVo::setId, detail.getId())
                .with(DictVo::setDictCode, detail.getDictCode())
                .with(DictVo::setDictValue, detail.getDictValue())
                .with(DictVo::setSort, detail.getSort())
                .build();
    }

    /**
     * <p>BmsDictDetail 转换为 DictVo</p>
     *
     * @param detail detail
     * @return org.starrism.mall.base.domain.vo.DictVo
     * @author hedwing
     * @since 2022/8/31
     */
    public static DictVo dictDetailToVo(BmsDictDetail detail) {
        return dictDetailToVoConverter(detail).convert(detail);
    }
}
