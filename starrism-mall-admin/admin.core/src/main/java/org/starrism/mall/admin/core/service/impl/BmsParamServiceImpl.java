package org.starrism.mall.admin.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.api.domain.dto.BmsParamQueryDto;
import org.starrism.mall.admin.core.mapper.BmsParamMapper;
import org.starrism.mall.admin.core.service.BmsParamService;
import org.starrism.mall.base.domain.converter.ParamConverters;
import org.starrism.mall.base.domain.entity.BmsParam;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.data.domain.vo.PageData;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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


    /**
     * <p>分页查询系统参数</p>
     *
     * @param dto dto
     * @return org.starrism.mall.data.domain.vo.PageData<org.starrism.mall.base.domain.vo.BmsParamVo>
     * @author hedwing
     * @since 2022/9/25
     */
    @Override
    public PageData<BmsParamVo> queryPage(BmsParamQueryDto dto) {
        LambdaQueryWrapper<BmsParam> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getId() != null, BmsParam::getId, dto.getId())
                .eq(StrUtil.isNotBlank(dto.getParamCode()), BmsParam::getParamCode, dto.getParamCode())
                .eq(StrUtil.isNotBlank(dto.getGroupCode()), BmsParam::getGroupCode, dto.getGroupCode())
                .like(StrUtil.isNotBlank(dto.getParamName()), BmsParam::getParamName, dto.getParamName());
        Page<BmsParam> page = new Page<>(dto.getCurrPage(), dto.getPageSize());
        page = bmsParamMapper.selectPage(page, wrapper);
        List<BmsParamVo> list = page.getRecords().stream().map(param -> {
            return ParamConverters.toParamVo(param);
        }).collect(Collectors.toList());
        return PageData.of(list, page.getTotal(), dto);
    }
}
