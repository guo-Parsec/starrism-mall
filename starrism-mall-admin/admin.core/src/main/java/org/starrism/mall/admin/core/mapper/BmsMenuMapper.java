package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.starrism.mall.admin.core.domain.entity.BmsMenu;

import java.util.List;

/**
 * <p>菜单Mapper</p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
@Mapper
public interface BmsMenuMapper {
    /**
     * 查询全部菜单数据
     *
     * @return 菜单数据
     */
    List<BmsMenu> findAll();
}