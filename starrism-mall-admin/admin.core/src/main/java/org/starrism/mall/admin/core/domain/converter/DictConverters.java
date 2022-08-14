package org.starrism.mall.admin.core.domain.converter;

import org.springframework.stereotype.Component;
import org.starrism.mall.admin.core.domain.entity.BmsDictDetail;
import org.starrism.mall.common.support.Convertible;
import org.starrism.mall.data.domain.vo.DictVo;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/14
 **/
@Component
public class DictConverters {

    Convertible<BmsDictDetail, DictVo> dictDetailToVoConverter(BmsDictDetail detail) {
        return (source) -> {
            if (source == null) {
                return new DictVo();
            }
            DictVo dictVo = new DictVo();
            dictVo.setDictCode(detail.getDictCode());
            dictVo.setDictValue(detail.getDictValue());
            dictVo.setSort(detail.getSort());
            return dictVo;
        };
    }

    public DictVo dictDetailToVo(BmsDictDetail detail) {
        return dictDetailToVoConverter(detail).convert(detail);
    }
}
