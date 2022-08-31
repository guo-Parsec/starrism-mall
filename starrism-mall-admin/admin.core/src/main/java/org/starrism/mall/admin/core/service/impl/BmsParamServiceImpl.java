package org.starrism.mall.admin.core.service.impl;

import org.springframework.stereotype.Service;
import org.starrism.mall.admin.core.mapper.BmsParamMapper;
import org.starrism.mall.admin.core.service.BmsParamService;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;

import javax.annotation.Resource;

/**
 * <p>基础参数业务类</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Service("bmsParamService")
public class BmsParamServiceImpl implements BmsParamService {
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(BmsParamServiceImpl.class);

    @Resource
    private BmsParamMapper bmsParamMapper;


}
