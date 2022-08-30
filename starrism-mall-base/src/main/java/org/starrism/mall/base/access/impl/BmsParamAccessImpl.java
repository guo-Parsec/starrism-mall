package org.starrism.mall.base.access.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.starrism.mall.base.access.BmsParamAccess;
import org.starrism.mall.base.domain.converter.BmsParamConverters;
import org.starrism.mall.base.domain.entity.BmsParam;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.base.repository.BmsParamRepository;

import javax.annotation.Resource;

/**
 * <p>基础参数业务类</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Component("bmsParamAccess")
public class BmsParamAccessImpl implements BmsParamAccess {
    private static final Logger log = LoggerFactory.getLogger(BmsParamAccessImpl.class);

    @Resource
    private BmsParamRepository bmsParamRepository;

    private BmsParamConverters bmsParamConverters;

    @Autowired
    public void setBmsParamConverters(BmsParamConverters bmsParamConverters) {
        this.bmsParamConverters = bmsParamConverters;
    }

    /**
     * <p>根据参数码获取参数</p>
     *
     * @param paramCode 参数码
     * @return org.starrism.mall.admin.api.domain.vo.BmsParamVo
     * @author hedwing
     * @since 2022/8/29
     */
    @Override
    @Cacheable(key = "#paramCode", cacheNames = "param:paramCode")
    public BmsParamVo findByParamCode(String paramCode) {
        if (log.isDebugEnabled()) {
            log.debug("查询paramCode为{}的参数", paramCode);
        }
        BmsParam param = bmsParamRepository.findByParamCode(paramCode);
        if (param == null) {
            if (log.isDebugEnabled()) {
                log.debug("查询paramCode为{}的参数为空", paramCode);
            }
            return null;
        }
        return bmsParamConverters.toParamVo(param);
    }
}
