package org.starrism.mall.base.repository;

import org.apache.ibatis.annotations.Param;
import org.starrism.mall.base.domain.entity.BmsResource;
import org.starrism.mall.data.mapper.CoreMapper;

import java.util.List;
import java.util.Set;

/**
 * <p>针对表【bms_resource(系统资源表)】的数据库操作Mapper</p>
 *
 * @author hedwing
 * @since 2022-08-31
 */
public interface BmsResourceRepository extends CoreMapper<BmsResource> {
    /**
     * <p>根据资源类别码查询资源信息</p>
     *
     * @param categoryCode 资源类别码
     * @return java.util.List<org.starrism.mall.base.domain.entity.BmsResource>
     * @author hedwing
     * @since 2022/8/31
     */
    List<BmsResource> findByCategoryCode(@Param("categoryCode") String categoryCode);

    /**
     * <p>根据角色id集合查询resourceCode</p>
     *
     * @param roleIdSet 角色id集合
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/8/31
     */
    Set<String> findResourceCodeByRole(@Param("roleIdSet") Set<Long> roleIdSet);
}




