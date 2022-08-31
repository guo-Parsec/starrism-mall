package org.starrism.mall.base.access.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.starrism.mall.base.access.ResourceAccess;
import org.starrism.mall.base.domain.converter.ResourceConverters;
import org.starrism.mall.base.domain.entity.BmsResource;
import org.starrism.mall.base.domain.vo.ResourceVo;
import org.starrism.mall.base.repository.BmsResourceRepository;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
import org.starrism.mall.common.util.CollectionUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>资源访问层实现类</p>
 *
 * @author hedwing
 * @since 2022/8/31
 **/
@Component("resourceAccess")
public class ResourceAccessImpl implements ResourceAccess {
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(ResourceAccessImpl.class);
    @Resource
    private BmsResourceRepository resourceRepository;


    /**
     * 根据参数码获取参数信息
     *
     * @param categoryCode 类别码
     * @return 参数信息
     */
    @Override
    public  List<ResourceVo> findByCategoryCode(String categoryCode) {
        List<BmsResource> resources = resourceRepository.findByCategoryCode(categoryCode);
        if (CollectionUtil.isEmpty(resources)) {
            LOGGER.debug("查询categoryCode={}的资源为空", categoryCode);
            return Lists.newArrayList();
        }
        return resources.stream().map(ResourceConverters::toResourceVo).collect(Collectors.toList());
    }
}
