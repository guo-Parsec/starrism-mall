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
public class BasePageParam extends BaseParam {
    private static final long serialVersionUID = 83042109450770030L;

    /**
     * 当前页
     */
    private Integer currPage;

    /**
     * 页大小
     */
    private Integer pageSize;



}
