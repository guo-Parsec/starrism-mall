package org.starrism.mall.data.domain.param;

import lombok.Getter;
import lombok.Setter;

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
    protected Integer currPage;

    /**
     * 页大小
     */
    protected Integer pageSize;



}
