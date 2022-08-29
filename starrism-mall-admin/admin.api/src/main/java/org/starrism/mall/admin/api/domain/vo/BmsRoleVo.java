package org.starrism.mall.admin.api.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;

/**
 * <p>角色信息展示数据</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Setter
@Getter
public class BmsRoleVo implements Domainizable {
    private static final long serialVersionUID = -7794649683830381741L;

    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 角色标识
     */
    @ApiModelProperty(value = "角色标识")
    private String roleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    private String description;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 层级路径
     */
    @ApiModelProperty(value = "层级路径")
    private String hierarchicalPath;

    /**
     * 上级id
     */
    @ApiModelProperty(value = "上级id")
    private String parentId;
}
