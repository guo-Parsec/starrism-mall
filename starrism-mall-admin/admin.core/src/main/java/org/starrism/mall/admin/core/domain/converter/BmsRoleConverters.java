package org.starrism.mall.admin.core.domain.converter;

import org.starrism.mall.admin.api.domain.vo.BmsRoleVo;
import org.starrism.mall.admin.core.domain.entity.BmsRole;
import org.starrism.mall.common.domain.BaseConverters;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.support.Convertible;

/**
 * <p>角色数据转换</p>
 *
 * @author hedwing
 * @since 2022/8/14
 **/
public class BmsRoleConverters implements BaseConverters {

    /**
     * <p>BmsRole 转 BmsRoleVo 转换器</p>
     *
     * @param bmsRole 角色实体
     * @author hedwing
     * @since 2022/8/14
     */
    static Convertible<BmsRole, BmsRoleVo> toRoleVoConverters(BmsRole bmsRole) {
        return (source) -> Builder.of(BmsRoleVo::new)
                .with(BmsRoleVo::setId, bmsRole.getId())
                .with(BmsRoleVo::setParentId, String.valueOf(bmsRole.getParentId()))
                .with(BmsRoleVo::setHierarchicalPath, bmsRole.getHierarchicalPath())
                .with(BmsRoleVo::setRoleCode, bmsRole.getRoleCode())
                .with(BmsRoleVo::setRoleName, bmsRole.getRoleName())
                .with(BmsRoleVo::setDescription, bmsRole.getDescription())
                .with(BmsRoleVo::setSort, bmsRole.getSort())
                .build();
    }

    /**
     * <p>BmsRole 转 BmsRoleVo</p>
     *
     * @param bmsRole bmsRole
     * @return org.starrism.mall.admin.api.domain.vo.BmsRoleVo
     * @author hedwing
     * @since 2022/8/31
     */
    public static BmsRoleVo toRoleVo(BmsRole bmsRole) {
        return toRoleVoConverters(bmsRole).convert(bmsRole);
    }

}
