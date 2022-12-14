package org.starrism.mall.base.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.starrism.mall.base.domain.entity.BmsDictDetail;
import org.starrism.mall.data.mapper.CoreMapper;

import java.util.List;

/**
 * <p>数据字典详情访问层</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Mapper
@Repository(value = "bmsDictDetailRepository")
public interface BmsDictDetailRepository extends CoreMapper<BmsDictDetail> {
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

    /**
     * <p>根据类别和字典码查询唯一字典</p>
     *
     * @param categoryId 类别id
     * @param dictCode   字典码
     * @return org.starrism.mall.data.domain.entity.BmsDictDetail
     * @author hedwing
     * @since 2022/8/14
     */
    BmsDictDetail findByCodes(@Param("categoryId") Long categoryId,
                              @Param("dictCode") String dictCode);
}