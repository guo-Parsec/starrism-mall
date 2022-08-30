package org.starrism.mall.admin.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.core.mapper.BmsParamMapper;
import org.starrism.mall.admin.core.service.BmsParamService;

import javax.annotation.Resource;

/**
 * <p>基础参数业务类</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Service("bmsParamService")
public class BmsParamServiceImpl implements BmsParamService {
    private static final Logger log = LoggerFactory.getLogger(BmsParamServiceImpl.class);

    @Resource
    private BmsParamMapper bmsParamMapper;


}
