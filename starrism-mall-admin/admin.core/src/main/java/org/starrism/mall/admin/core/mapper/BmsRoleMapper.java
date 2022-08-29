package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.starrism.mall.admin.core.domain.entity.BmsRole;
import org.starrism.mall.data.mapper.CoreMapper;

import java.util.List;

/**
 * <p>针对表【bms_role(系统角色表)】的数据库操作Mapper</p>
 *
 * @author hedwing
 * @since 2022-08-29 15:31:50
 **/
@Mapper
@Repository
public interface BmsRoleMapper extends CoreMapper<BmsRole> {
    /**
     * <p>根据userId查询角色信息</p>
     *
     * @param userId userId
     * @return java.util.List<org.starrism.mall.admin.core.domain.entity.BmsRole>
     * @author hedwing
     * @since 2022/8/29
     */
    List<BmsRole> findByUser(@Param("userId") Long userId);
}




