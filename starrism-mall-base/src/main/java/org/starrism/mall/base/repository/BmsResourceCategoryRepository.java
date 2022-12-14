package org.starrism.mall.base.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.starrism.mall.base.domain.entity.BmsResourceCategory;
import org.starrism.mall.data.mapper.CoreMapper;

/**
 * <p>针对表【bms_resource_category(系统资源类别表)】的数据库操作Mapper</p>
 *
 * @author hedwing
 */
@Mapper
@Repository(value = "bmsResourceCategoryRepository")
public interface BmsResourceCategoryRepository extends CoreMapper<BmsResourceCategory> {
    /**
     * <p>根据分类码查询分类信息</p>
     *
     * @param categoryCode 分类码
     * @return org.starrism.mall.base.domain.entity.BmsResourceCategory
     * @author hedwing
     * @since 2022/8/31
     */
    BmsResourceCategory findByCategoryCode(@Param("categoryCode") String categoryCode);
}




