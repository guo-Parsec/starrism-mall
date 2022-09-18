package org.starrism.mall.base.access.impl;

import com.google.common.collect.Lists;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.starrism.mall.base.access.ParamAccess;
import org.starrism.mall.base.domain.converter.ParamConverters;
import org.starrism.mall.base.domain.entity.BmsParam;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.base.repository.BmsParamRepository;
import org.starrism.mall.common.exceptions.StarrismException;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
import org.starrism.mall.common.rest.ResultCode;
import org.starrism.mall.common.util.CollectionUtil;
import org.starrism.mall.common.util.StrUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * <p>根据组别和参数码(可选)获取参数信息</p>
     *
     * @param groupCode 组别
     * @param paramCode 参数码(可选)
     * @return java.util.List<org.starrism.mall.base.domain.vo.BmsParamVo>
     * @author hedwing
     * @since 2022/9/17
     */
    @Override
    @Cacheable(key = "'g'-#groupCode +'-'+#paramCode", cacheNames = "param:paramCode")
    public List<BmsParamVo> findByGroupCode(String groupCode, String paramCode) {
        LOGGER.debug("根据组别和参数码(可选)获取参数信息:参数[groupCode]={},参数[paramCode]={}", groupCode, paramCode);
        if (StrUtil.isBlank(groupCode)) {
            LOGGER.error("参数[groupCode]不能为空");
            throw new StarrismException("参数[groupCode]不能为空", ResultCode.VALIDATE_FAILED);
        }
        if (StrUtil.isBlank(paramCode)) {
            LOGGER.debug("根据组别和参数码(可选)获取参数信息:参数[groupCode]={},参数[paramCode]为空", groupCode);
        }
        List<BmsParam> paramList = bmsParamRepository.findByGroupCode(groupCode, paramCode);
        if (CollectionUtil.isEmpty(paramList)) {
            LOGGER.warn("根据组别和参数码(可选)获取参数信息:参数[groupCode]={},参数[paramCode]={}查询到的数据为空", groupCode, paramCode);
            return Lists.newArrayList();
        }
        return paramList.stream().map(ParamConverters::toParamVo).collect(Collectors.toList());
    }


}
