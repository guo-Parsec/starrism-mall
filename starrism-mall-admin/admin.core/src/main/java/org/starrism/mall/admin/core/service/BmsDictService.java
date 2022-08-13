package org.starrism.mall.admin.core.service;

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
public class BmsDictService {
    @Resource
    private BmsDictCategoryMapper dictCategoryMapper;
    @Resource
    private BmsDictDetailMapper dictDetailMapper;

    @Cacheable(key = "#categoryCode", cacheNames = "dict:categoryCode")
    public List<BmsDictVo> findDictByCategoryCode(String categoryCode) {
        BmsDictCategory category = dictCategoryMapper.findByCode(categoryCode);
        if (category == null) {
            return null;
        }
        List<BmsDictDetail> details = dictDetailMapper.findByCategory(category.getId());
        return details.stream().map(detail -> {
            BmsDictVo bmsDictVo = new BmsDictVo();
            bmsDictVo.setDictCode(detail.getDictCode());
            bmsDictVo.setDictValue(detail.getDictValue());
            bmsDictVo.setSort(detail.getSort());
            return bmsDictVo;
        }).collect(Collectors.toList());
    }
}
