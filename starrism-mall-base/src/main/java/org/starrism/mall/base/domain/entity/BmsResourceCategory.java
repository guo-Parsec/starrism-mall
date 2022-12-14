package org.starrism.mall.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.data.domain.entity.BaseDataEntity;

/**
 * 系统资源类别表
 * @author hedwing
 * @TableName bms_resource_category
 */
@TableName(value ="bms_resource_category")
@Setter
@Getter
public class BmsResourceCategory extends BaseDataEntity  {

    private static final long serialVersionUID = -3276040546765231019L;

    /**
     * 资源类别码
     */
    private String categoryCode;

    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private String sort;

    /**
     * 资源类别描述
     */
    @TableField(value = "`description`")
    private String description;
}