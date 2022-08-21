package org.starrism.mall.admin.core.service.impl;

import org.springframework.stereotype.Service;
import org.starrism.mall.admin.core.domain.entity.BmsMenu;
import org.starrism.mall.admin.core.mapper.BmsMenuMapper;
import org.starrism.mall.admin.core.service.BmsMenuService;
import org.starrism.mall.common.support.TreeProcessor;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/21
 **/
@Service
public class BmsMenuServiceImpl implements BmsMenuService {
    @Resource(name = "recursionTreeProcessor")
    private TreeProcessor<BmsMenu> treeProcessor;
    @Resource
    private BmsMenuMapper bmsMenuMapper;

    /**
     * <p>查询全部菜单数据</p>
     *
     * @return java.util.List<org.starrism.mall.admin.core.domain.entity.BmsMenu>
     * @author hedwing
     * @since 2022/8/20
     */
    @Override
    public List<BmsMenu> findAll() {
        List<BmsMenu> allMenus = bmsMenuMapper.findAll();
        return treeProcessor.toTree(allMenus, 0L);
    }

    /**
     * <p>查询全部菜单数据</p>
     *
     * @return java.util.List<org.starrism.mall.admin.core.domain.entity.BmsMenu>
     * @author hedwing
     * @since 2022/8/21
     */
    @Override
    public List<BmsMenu> listAll() {
        return bmsMenuMapper.findAll();
    }
}
