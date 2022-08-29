package org.starrism.mall.admin.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.api.domain.vo.BmsParamVo;
import org.starrism.mall.admin.core.domain.converter.BmsParamConverters;
import org.starrism.mall.admin.core.domain.entity.BmsParam;
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
    public BmsParamVo findByParamCode(String paramCode) {
        if (log.isDebugEnabled()) {
            log.debug("查询paramCode为{}的参数", paramCode);
        }
        BmsParam param = bmsParamMapper.findByParamCode(paramCode);
        if (param == null) {
            if (log.isDebugEnabled()) {
                log.debug("查询paramCode为{}的参数为空", paramCode);
            }
            return null;
        }
        return bmsParamConverters.toParamVo(param);
    }
}
