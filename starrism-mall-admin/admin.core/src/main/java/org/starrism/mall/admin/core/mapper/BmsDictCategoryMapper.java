package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.starrism.mall.admin.api.domain.entity.BmsDictCategory;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Mapper
public interface BmsDictCategoryMapper {
    /**
     * <p>根据类别码获取类别信息</p>
     *
     * @param dictCategoryCode 类别码
     * @return org.starrism.mall.admin.api.domain.entity.BmsDictCategory
     * @author hedwing
     * @since 2022/8/13
     */
    BmsDictCategory findByCode(@Param("dictCategoryCode") String dictCategoryCode);
}
