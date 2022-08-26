package org.starrism.mall.admin.api.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;

import java.util.List;
import java.util.Set;

/**
 * <p>认证用户</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Setter
@Getter
public class AuthUser implements Domainizable {
    private static final long serialVersionUID = 7985972063033198360L;
    private BmsUserVo userInfo;

    private Set<String> roles;

    private Set<String> permissions;

    private List<String> clients;
}
