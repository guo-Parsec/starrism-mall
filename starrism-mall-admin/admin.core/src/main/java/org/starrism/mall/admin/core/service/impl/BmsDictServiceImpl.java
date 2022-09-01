package org.starrism.mall.admin.core.service.impl;

import org.springframework.stereotype.Service;
import org.starrism.mall.admin.core.mapper.BmsDictCategoryMapper;
import org.starrism.mall.admin.core.mapper.BmsDictDetailMapper;
import org.starrism.mall.admin.core.service.BmsDictService;

import javax.annotation.Resource;

/**
 * <p>字典业务类</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Service("bmsDictService")
public class BmsDictServiceImpl implements BmsDictService {
    @Resource
    private BmsDictCategoryMapper dictCategoryMapper;
    @Resource
    private BmsDictDetailMapper dictDetailMapper;

}
