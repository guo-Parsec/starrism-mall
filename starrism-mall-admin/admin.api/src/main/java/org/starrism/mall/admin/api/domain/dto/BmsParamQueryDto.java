package org.starrism.mall.admin.api.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.data.domain.param.BasePageDto;

/**
 * 系统参数表
 *
 * @author hedwing
 * @TableName bms_param
 */
@Setter
@Getter
public class BmsParamQueryDto extends BasePageDto implements Domainizable {

    private static final long serialVersionUID = -8861751454077998874L;
    @ApiModelProperty(value = "系统参数表主键")
    private Long id;

    /**
     * 组别
     */
    @ApiModelProperty(value = "组别")
    private String groupCode;

    /**
     * 参数标识
     */
    @ApiModelProperty(value = "参数标识")
    private String paramCode;

    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    private String paramValue;

    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    private String paramName;

}