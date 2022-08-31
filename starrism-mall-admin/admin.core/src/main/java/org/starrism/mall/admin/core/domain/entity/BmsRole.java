package org.starrism.mall.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.starrism.mall.data.domain.entity.AbstractDataEntityTree;

/**
 * <p>系统角色表</p>
 *
 * @author hedwing
 * @since 2022/8/29
 */
@Setter
@Getter
@ToString
@TableName(value = "bms_role")
public class BmsRole extends AbstractDataEntityTree<BmsRole> {
    private static final long serialVersionUID = -1450833595279126423L;

    /**
     * 角色标识
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    @TableField(value = "`description`")
    private String description;

    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private Integer sort;

    /**
     * 层级路径
     */
    private String hierarchicalPath;

    @Override
    public BmsRole clone() throws CloneNotSupportedException {
        return (BmsRole) super.clone();
    }
}