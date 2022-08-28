package org.starrism.mall.data.domain.converter;

import org.springframework.stereotype.Component;
import org.starrism.mall.data.domain.entity.BmsDictDetail;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.support.Convertible;
import org.starrism.mall.data.domain.vo.DictVo;

/**
 * <p>字典转换</p>
 *
 * @author hedwing
 * @since 2022/8/14
 **/
@Component
public class DictConverters {

    Convertible<BmsDictDetail, DictVo> dictDetailToVoConverter(BmsDictDetail detail) {
        return (source) -> Builder.of(DictVo::new)
                .with(DictVo::setId, detail.getId())
                .with(DictVo::setDictCode, detail.getDictCode())
                .with(DictVo::setDictValue, detail.getDictValue())
                .with(DictVo::setSort, detail.getSort())
                .build();
    }

    public DictVo dictDetailToVo(BmsDictDetail detail) {
        return dictDetailToVoConverter(detail).convert(detail);
    }
}
