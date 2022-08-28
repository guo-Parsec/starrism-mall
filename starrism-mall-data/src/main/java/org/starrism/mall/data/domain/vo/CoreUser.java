package org.starrism.mall.data.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.support.CommonConverts;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.data.service.client.DictClient;

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
        return DictClient.convert("SEX", CommonConverts.toStr().convert(this.sex, StrUtil.EMPTY));
    }

    @Override
    public CoreUser clone() throws CloneNotSupportedException {
        return (CoreUser) super.clone();
    }


}
