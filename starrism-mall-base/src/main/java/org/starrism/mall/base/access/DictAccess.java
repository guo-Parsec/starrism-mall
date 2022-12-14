package org.starrism.mall.base.access;

import org.starrism.mall.base.domain.vo.DictVo;

import java.util.List;

/**
 * <p>字典参数接口</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public interface DictAccess {
    /**
     * 根据分类码查询字典
     *
     * @param categoryCode 分类码
     * @return 字典
     */
    List<DictVo> findDictByCategoryCode(String categoryCode);

    /**
     * <p>根据分类码与字典码查询字典</p>
     *
     * @param categoryCode 分类码
     * @param dictCode     字典码
     * @return org.starrism.mall.data.domain.vo.DictVo
     * @author hedwing
     * @since 2022/8/14
     */
    DictVo findDictByCodes(String categoryCode, String dictCode);
}
