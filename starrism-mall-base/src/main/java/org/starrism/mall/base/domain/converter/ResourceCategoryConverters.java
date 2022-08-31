package org.starrism.mall.base.domain.converter;

import org.starrism.mall.base.domain.entity.BmsResourceCategory;
import org.starrism.mall.base.domain.vo.ResourceCategoryVo;
import org.starrism.mall.common.domain.BaseConverters;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.support.Convertible;

/**
 * <p>资源转换器</p>
 *
 * @author hedwing
 * @since 2022/8/31
 **/
public class ResourceCategoryConverters implements BaseConverters {
    /**
     * <p>BmsResourceCategory 转 ResourceCategoryVo 转换器</p>
     *
     * @param bmsResourceCategory bmsResourceCategory
     * @return org.starrism.mall.common.support.Convertible<org.starrism.mall.base.domain.entity.BmsResourceCategory, org.starrism.mall.base.domain.vo.ResourceCategoryVo>
     * @author hedwing
     * @since 2022/8/31
     */
    static Convertible<BmsResourceCategory, ResourceCategoryVo> toResourceCategoryVoConverters(BmsResourceCategory bmsResourceCategory) {
        return (source) -> Builder.of(ResourceCategoryVo::new)
                .with(ResourceCategoryVo::setId, bmsResourceCategory.getId())
                .with(ResourceCategoryVo::setCategoryCode, bmsResourceCategory.getCategoryCode())
                .with(ResourceCategoryVo::setSort, bmsResourceCategory.getSort())
                .with(ResourceCategoryVo::setDescription, bmsResourceCategory.getDescription())
                .build();
    }

    /**
     * <p>BmsResourceCategory 转 ResourceCategoryVo</p>
     *
     * @param bmsResourceCategory bmsResourceCategory
     * @return org.starrism.mall.base.domain.vo.ResourceCategoryVo
     * @author hedwing
     * @since 2022/8/31
     */
    public static ResourceCategoryVo toResourceCategoryVo(BmsResourceCategory bmsResourceCategory) {
        return toResourceCategoryVoConverters(bmsResourceCategory).convert(bmsResourceCategory);
    }
}
