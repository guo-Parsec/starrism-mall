package org.starrism.mall.common.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;

/**
 * <p>Token展示层</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Setter
@Getter
public class AccessTokenVo implements Domainizable {
    private static final long serialVersionUID = 6667485212923644457L;
    /**
     * token名称
     */
    private String accessToken;

    /**
     * token值
     */
    private String accessTokenName;
}
