package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.starrism.mall.admin.api.domain.entity.BmsDictDetail;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Mapper
public interface BmsDictDetailMapper {
    /**
     * <p>根据主键查询字典</p>
     *
     * @param id 主键
     * @return org.starrism.mall.admin.api.domain.entity.BmsDictCategory
     * @author hedwing
     * @since 2022/8/13
     */
    BmsDictDetail findById(@Param("id") Long id);

    /**
     * <p>根据类别查询字典</p>
     *
     * @param categoryId 类别id
     * @return java.util.List<org.starrism.mall.admin.api.domain.entity.BmsDictDetail>
     * @author hedwing
     * @since 2022/8/13
     */
    List<BmsDictDetail> findByCategory(Long categoryId);
}