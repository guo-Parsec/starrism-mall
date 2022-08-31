package org.starrism.mall.base.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.starrism.mall.base.domain.entity.BmsDictCategory;
import org.starrism.mall.data.mapper.CoreMapper;

/**
 * <p>数据字典类别访问层</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Mapper
@Repository(value = "bmsDictCategoryRepository")
public interface BmsDictCategoryRepository extends CoreMapper<BmsDictCategory> {
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
