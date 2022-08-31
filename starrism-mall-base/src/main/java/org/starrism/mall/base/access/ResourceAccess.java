package org.starrism.mall.base.access;

import org.apache.ibatis.annotations.Param;
import org.starrism.mall.base.domain.vo.ResourceVo;

import java.util.List;

/**
 * <p>资源访问层</p>
 *
 * @author hedwing
 * @since 2022/8/31
 **/
public interface ResourceAccess {
    /**
     * 根据参数码获取参数信息
     *
     * @param categoryCode 类别码
     * @return 参数信息
     */
    List<ResourceVo> findByCategoryCode(@Param("categoryCode") String categoryCode);
}
