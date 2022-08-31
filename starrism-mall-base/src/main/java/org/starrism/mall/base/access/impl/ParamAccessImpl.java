package org.starrism.mall.base.access.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.starrism.mall.base.access.ParamAccess;
import org.starrism.mall.base.domain.converter.ParamConverters;
import org.starrism.mall.base.domain.entity.BmsParam;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.base.repository.BmsParamRepository;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;

import javax.annotation.Resource;

/**
 * <p>基础参数业务类</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Component("paramAccess")
public class ParamAccessImpl implements ParamAccess {
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(ParamAccessImpl.class);

    @Resource
    private BmsParamRepository bmsParamRepository;

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
        LOGGER.debug("查询paramCode为{}的参数", paramCode);
        BmsParam param = bmsParamRepository.findByParamCode(paramCode);
        if (param == null) {
            LOGGER.debug("查询paramCode为{}的参数为空", paramCode);
            return null;
        }
        return ParamConverters.toParamVo(param);
    }
}
