package org.starrism.mall.base.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;

/**
 * <p>参数数据展示</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Setter
@Getter
public class BmsParamVo implements Domainizable {
    private static final long serialVersionUID = 700826017527327912L;

    @ApiModelProperty(value = "主键")
    private Long id;

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
