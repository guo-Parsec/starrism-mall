package org.starrism.mall.auth.core.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.admin.api.domain.vo.BmsUserVo;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.domain.vo.AccessTokenVo;

import java.util.Set;

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
    private BmsUserVo userVo;

    private Set<String> roles;

    private Set<String> permissions;

    private AccessTokenVo accessToken;
}
