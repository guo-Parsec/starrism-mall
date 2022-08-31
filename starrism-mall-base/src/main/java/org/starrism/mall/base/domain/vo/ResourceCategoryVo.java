package org.starrism.mall.base.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;

/**
 * <p>资源类别展示层</p>
 *
 * @author hedwing
 * @since 2022/8/31
 **/
@Setter
@Getter
public class ResourceCategoryVo implements Domainizable {
    private static final long serialVersionUID = 6096303036776878320L;

    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 资源类别码
     */
    @ApiModelProperty(value = "资源类别码")
    private String categoryCode;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private String sort;

    /**
     * 资源类别描述
     */
    @ApiModelProperty(value = "资源类别描述")
    private String description;
}
