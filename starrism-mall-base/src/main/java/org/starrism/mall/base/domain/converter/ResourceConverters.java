package org.starrism.mall.base.domain.converter;

import org.starrism.mall.base.domain.entity.BmsResource;
import org.starrism.mall.base.domain.entity.BmsResourceCategory;
import org.starrism.mall.base.domain.vo.ResourceVo;
import org.starrism.mall.common.domain.BaseConverters;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.support.Convertible;

/**
 * <p>资源转换器</p>
 *
 * @author hedwing
 * @since 2022/8/31
 **/
public class ResourceConverters implements BaseConverters {
    /**
     * <p>BmsResource 转 ResourceVo 转换器</p>
     *
     * @param bmsResource bmsResource
     * @return org.starrism.mall.common.support.Convertible<org.starrism.mall.base.domain.entity.BmsResource, org.starrism.mall.base.domain.vo.ResourceVo>
     * @author hedwing
     * @since 2022/8/31
     */
    static Convertible<BmsResource, ResourceVo> toResourceVoConverters(BmsResource bmsResource) {
        return (source) -> Builder.of(ResourceVo::new)
                .with(ResourceVo::setId, bmsResource.getId())
                .with(ResourceVo::setResourceCategoryVo, null)
                .with(ResourceVo::setResourceCode, bmsResource.getResourceCode())
                .with(ResourceVo::setResourceUrl, bmsResource.getResourceUrl())
                .with(ResourceVo::setDescription, bmsResource.getDescription())
                .build();
    }

    /**
     * <p>BmsResource 转 ResourceVo</p>
     *
     * @param bmsResource         bmsResource
     * @return org.starrism.mall.base.domain.vo.ResourceVo
     * @author hedwing
     * @since 2022/8/31
     */
    public static ResourceVo toResourceVo(BmsResource bmsResource) {
        return toResourceVo(bmsResource, null);
    }

    /**
     * <p>BmsResource 转 ResourceVo</p>
     *
     * @param bmsResource         bmsResource
     * @param bmsResourceCategory bmsResourceCategory
     * @return org.starrism.mall.base.domain.vo.ResourceVo
     * @author hedwing
     * @since 2022/8/31
     */
    public static ResourceVo toResourceVo(BmsResource bmsResource, BmsResourceCategory bmsResourceCategory) {
        ResourceVo resourceVo = toResourceVoConverters(bmsResource).convert(bmsResource);
        resourceVo.setResourceCategoryVo(ResourceCategoryConverters.toResourceCategoryVo(bmsResourceCategory));
        return resourceVo;
    }
}
