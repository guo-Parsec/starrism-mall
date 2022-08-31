package org.starrism.mall.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.data.domain.entity.BaseDataEntity;

/**
 * <p>系统资源表</p>
 *
 * @TableName bms_resource
 * @author hedwing
 * @since 2022/8/31
 **/
@TableName("bms_resource")
@Setter
@Getter
public class BmsResource extends BaseDataEntity {
    private static final long serialVersionUID = 2850001065107998306L;

    /**
     * 资源分类id
     */
    private Long resourceCategoryId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源路径
     */
    private String resourceUrl;

    /**
     * 资源描述
     */
    private String description;
}
