package org.starrism.mall.admin.api.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.starrism.mall.common.domain.Domainizable;

import javax.validation.constraints.NotEmpty;

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

    @NotEmpty(message = "登录用户名不能为空")
    @ApiModelProperty(value = "登录用户名")
    private String username;

    @NotEmpty(message = "登录密码不能为空")
    @Length(min = 1, max = 16, message = "登录密码不能超过16位")
    @ApiModelProperty(value = "登录密码")
    private String password;
}
