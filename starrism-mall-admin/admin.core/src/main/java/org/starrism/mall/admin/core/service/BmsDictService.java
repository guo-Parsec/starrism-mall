package org.starrism.mall.admin.core.service;

import org.starrism.mall.data.component.DictComponent;
import org.starrism.mall.data.domain.vo.DictVo;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public interface BmsDictService extends DictComponent {
    /**
     * 根据分类码查询字典
     *
     * @param categoryCode 分类码
     * @return 字典
     */
    List<DictVo> findDictByCategoryCode(String categoryCode);
}
