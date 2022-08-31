package org.starrism.mall.base.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.base.context.DictContext;
import org.starrism.mall.common.domain.Domainizable;

import java.util.Set;

/**
 * <p>统一用户</p>
 *
 * @author hedwing
 * @since 2022/8/28
 **/
@Setter
@Getter
public class CoreUser implements Domainizable {
    private static final long serialVersionUID = 3195566414161390513L;

    private Long id;

    private String username;

    private String nickname;

    private String email;

    private String phoneNumber;

    private String avatarUrl;

    private String password;

    private Set<String> roles;

    private Set<String> permissions;

    private Integer sex;

    private String sexName;

    public String getSexName() {
        return DictContext.convert("SEX", this.sex);
    }

    @Override
    public CoreUser clone() throws CloneNotSupportedException {
        return (CoreUser) super.clone();
    }


}
