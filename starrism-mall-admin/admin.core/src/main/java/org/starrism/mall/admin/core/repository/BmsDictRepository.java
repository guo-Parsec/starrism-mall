package org.starrism.mall.admin.core.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.starrism.mall.admin.api.domain.entity.BmsDictCategory;
import org.starrism.mall.admin.api.domain.entity.BmsDictDetail;
import org.starrism.mall.admin.api.domain.vo.BmsDictVo;
import org.starrism.mall.admin.core.mapper.BmsDictCategoryMapper;
import org.starrism.mall.admin.core.mapper.BmsDictDetailMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Repository
@CacheConfig(cacheNames = "dict")
public class BmsDictRepository {
    @Resource
    private BmsDictCategoryMapper dictCategoryMapper;
    @Resource
    private BmsDictDetailMapper dictDetailMapper;

    @Cacheable(value = "dict", key = "'list'")
    public List<BmsDictVo> findDictByCategoryCode(String categoryCode) {
        BmsDictCategory category = dictCategoryMapper.findByCode(categoryCode);
        if (category == null) {
            return null;
        }
        List<BmsDictDetail> details = dictDetailMapper.findByCategory(category.getId());
        return details.stream().map(detail -> {
            BmsDictVo bmsDictVo = new BmsDictVo();
            bmsDictVo.setDictCode(detail.getDictCode());
            bmsDictVo.setDictCode(detail.getDictValue());
            bmsDictVo.setSort(detail.getSort());
            return bmsDictVo;
        }).collect(Collectors.toList());
    }
}
