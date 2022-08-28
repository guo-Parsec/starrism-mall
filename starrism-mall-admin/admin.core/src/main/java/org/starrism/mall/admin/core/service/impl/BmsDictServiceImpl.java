package org.starrism.mall.admin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.core.service.BmsDictService;
import org.starrism.mall.data.domain.converter.DictConverters;
import org.starrism.mall.data.mapper.BmsDictCategoryMapper;
import org.starrism.mall.data.mapper.BmsDictDetailMapper;
import org.starrism.mall.data.service.impl.DictServiceImpl;

import javax.annotation.Resource;

/**
 * <p>字典业务类</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Service("bmsDictService")
public class BmsDictServiceImpl extends DictServiceImpl implements BmsDictService {
    @Resource
    private BmsDictCategoryMapper dictCategoryMapper;
    @Resource
    private BmsDictDetailMapper dictDetailMapper;
    DictConverters dictConverters;

    @Autowired
    public void setDictConverters(DictConverters dictConverters) {
        this.dictConverters = dictConverters;
    }

}
