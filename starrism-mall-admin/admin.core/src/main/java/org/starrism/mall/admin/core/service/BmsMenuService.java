package org.starrism.mall.admin.core.service;

import org.starrism.mall.admin.core.domain.entity.BmsMenu;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public interface BmsMenuService {
    /**
     * <p>查询全部菜单数据【树状结构】</p>
     *
     * @return java.util.List<org.starrism.mall.admin.core.domain.entity.BmsMenu>
     * @author hedwing
     * @since 2022/8/20
     */
    List<BmsMenu> findAll();

    /**
     * <p>查询全部菜单数据</p>
     *
     * @return java.util.List<org.starrism.mall.admin.core.domain.entity.BmsMenu>
     * @author hedwing
     * @since 2022/8/21
     */
    List<BmsMenu> listAll();

    /**
     * <p>分页查询菜单数据</p>
     * @param
     * @return java.util.List<org.starrism.mall.admin.core.domain.entity.BmsMenu>
     * @author hedwing
     * @since 2022/8/21
     */
    List<BmsMenu> queryAll();
}
