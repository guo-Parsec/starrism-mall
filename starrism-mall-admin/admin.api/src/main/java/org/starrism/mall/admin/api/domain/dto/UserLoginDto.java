package org.starrism.mall.admin.api.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;

/**
 * <p>用户登录传参</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Setter
@Getter
public class UserLoginDto implements Domainizable {
    private static final long serialVersionUID = -1115450031216939160L;

    private String username;

    private String password;
}
