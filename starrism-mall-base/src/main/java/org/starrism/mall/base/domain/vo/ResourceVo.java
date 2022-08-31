package org.starrism.mall.base.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;

/**
 * <p>资源展示层</p>
 *
 * @author hedwing
 * @since 2022/8/31
 **/
@Setter
@Getter
public class ResourceVo implements Domainizable {
    private static final long serialVersionUID = 3722266617619088968L;

    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 资源分类id
     */
    @ApiModelProperty(value = "资源分类")
    private ResourceCategoryVo resourceCategoryVo;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String resourceCode;

    /**
     * 资源路径
     */
    @ApiModelProperty(value = "资源路径")
    private String resourceUrl;

    /**
     * 资源描述
     */
    @ApiModelProperty(value = "资源描述")
    private String description;
}
