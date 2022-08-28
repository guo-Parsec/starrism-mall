package org.starrism.mall.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.starrism.mall.data.domain.converter.DictConverters;
import org.starrism.mall.data.domain.entity.BmsDictCategory;
import org.starrism.mall.data.domain.entity.BmsDictDetail;
import org.starrism.mall.data.domain.vo.DictVo;
import org.starrism.mall.data.mapper.BmsDictCategoryMapper;
import org.starrism.mall.data.mapper.BmsDictDetailMapper;
import org.starrism.mall.data.service.DictService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/28
 **/
@Service("dictService")
public class DictServiceImpl implements DictService {
    @Resource
    private BmsDictCategoryMapper dictCategoryMapper;
    @Resource
    private BmsDictDetailMapper dictDetailMapper;
    DictConverters dictConverters;
    @Autowired
    public void setDictConverters(DictConverters dictConverters) {
        this.dictConverters = dictConverters;
    }

    /**
     * 根据分类码查询字典
     *
     * @param categoryCode 分类码
     * @return 字典
     */
    @Override
    @Cacheable(key = "#categoryCode", cacheNames = "dict:categoryCode")
    public List<DictVo> findDictByCategoryCode(String categoryCode) {
        BmsDictCategory category = dictCategoryMapper.findByCode(categoryCode);
        if (category == null) {
            return null;
        }
        List<BmsDictDetail> details = dictDetailMapper.findByCategory(category.getId());
        return details.stream().map(detail -> dictConverters.dictDetailToVo(detail)).collect(Collectors.toList());
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
        BmsDictCategory category = dictCategoryMapper.findByCode(categoryCode);
        if (category == null) {
            return null;
        }
        return dictConverters.dictDetailToVo(dictDetailMapper.findByCodes(category.getId(), dictCode));
    }
}
