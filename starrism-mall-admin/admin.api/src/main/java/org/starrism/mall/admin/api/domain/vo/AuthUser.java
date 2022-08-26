package org.starrism.mall.admin.api.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.starrism.mall.data.domain.vo.BaseVo;

import java.util.List;
import java.util.Set;

/**
 * <p>认证用户</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends BaseVo {
    private static final long serialVersionUID = 7985972063033198360L;
    private BmsUserVo userInfo;

    private Set<String> roles;

    private Set<String> permissions;

    private List<String> clients;
}
