package org.starrism.mall.auth.core.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.domain.vo.AccessTokenVo;
import org.starrism.mall.data.domain.vo.CoreUser;

/**
 * <p>认证信息展示层</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Setter
@Getter
public class AuthInfoVo implements Domainizable {
    private static final long serialVersionUID = 6005803474355067677L;

    private CoreUser coreUser;

    private AccessTokenVo accessToken;
}
