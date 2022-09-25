package org.starrism.mall.data.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/21
 **/
@Setter
@Getter
public class BasePageDto extends BaseDto {
    private static final long serialVersionUID = 83042109450770030L;

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 0, message = "当前页不能小于0")
    @ApiModelProperty(value = "当前页")
    protected Integer currPage;

    /**
     * 页大小
     */
    @NotNull(message = "页大小不能为空")
    @Min(value = 0, message = "页大小不能小于0")
    @ApiModelProperty(value = "页大小")
    protected Integer pageSize;


}
