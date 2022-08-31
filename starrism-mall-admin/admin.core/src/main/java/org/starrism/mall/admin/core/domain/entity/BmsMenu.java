package org.starrism.mall.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.starrism.mall.data.domain.entity.AbstractDataEntityTree;

/**
 * <p>系统菜单表</p>
 *
 * @author hedwing
 * @since 2022/8/20`
 **/
@Setter
@Getter
@ToString
@TableName("bms_menu")
public class BmsMenu extends AbstractDataEntityTree<BmsMenu> {
    private static final long serialVersionUID = 1399980644943450383L;

    /**
     * 层级路径
     */
    private String hierarchicalPath;

    /**
     * 菜单标识
     */
    private String menuCode;

    /**
     * 菜单标题
     */
    private String menuTitle;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 显示状态(数据字典 0-显示 1-隐藏)
     */
    private Integer visibleStatus;

    /**
     * 菜单级别
     */
    private Integer menuLevel;

    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private Integer sort;

    /**
     * 前端组件
     */
    private String component;

    /**
     * 菜单访问url
     */
    private String menuUrl;

    /**
     * 菜单描述
     */
    @TableField(value = "`description`")
    private String description;

}
