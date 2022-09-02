package org.starrism.mall.base.access.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.starrism.mall.base.access.DictAccess;
import org.starrism.mall.base.domain.converter.DictConverters;
import org.starrism.mall.base.domain.entity.BmsDictCategory;
import org.starrism.mall.base.domain.entity.BmsDictDetail;
import org.starrism.mall.base.domain.vo.DictVo;
import org.starrism.mall.base.pool.BeanPool;
import org.starrism.mall.base.repository.BmsDictCategoryRepository;
import org.starrism.mall.base.repository.BmsDictDetailRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>数据字典参数业务类</p>
 *
 * @author hedwing
 * @since 2022/8/28
 **/
@Service(BeanPool.DICT_ACCESS_BEAN_NAME)
public class DictAccessImpl implements DictAccess {
    @Resource
    private BmsDictCategoryRepository dictCategoryRepository;
    @Resource
    private BmsDictDetailRepository dictDetailRepository;

    /**
     * 根据分类码查询字典
     *
     * @param categoryCode 分类码
     * @return 字典
     */
    @Override
    @Cacheable(key = "#categoryCode", cacheNames = "dict:categoryCode")
    public List<DictVo> findDictByCategoryCode(String categoryCode) {
        BmsDictCategory category = dictCategoryRepository.findByCode(categoryCode);
        if (category == null) {
            return null;
        }
        List<BmsDictDetail> details = dictDetailRepository.findByCategory(category.getId());
        return details.stream().map(DictConverters::dictDetailToVo).collect(Collectors.toList());
    }

    /**
     * <p>根据分类码与字典码查询字典</p>
     *
     * @param categoryCode 分类码
     * @param dictCode     字典码
     * @return org.starrism.mall.data.domain.vo.DictVo
     * @author hedwing
     * @since 2022/8/14
     */
    @Override
    @Cacheable(key = "#categoryCode+'-'+#dictCode", cacheNames = "dict:categoryCode")
    public DictVo findDictByCodes(String categoryCode, String dictCode) {
        BmsDictCategory category = dictCategoryRepository.findByCode(categoryCode);
        if (category == null) {
            return null;
        }
        return DictConverters.dictDetailToVo(dictDetailRepository.findByCodes(category.getId(), dictCode));
    }
}
