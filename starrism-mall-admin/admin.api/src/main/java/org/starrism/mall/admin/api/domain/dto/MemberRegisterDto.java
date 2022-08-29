package org.starrism.mall.admin.api.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.exceptions.StarrismException;
import org.starrism.mall.common.rest.ResultCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * <p>用户注册传参</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Setter
@Getter
public class MemberRegisterDto implements Domainizable {
    private static final long serialVersionUID = -1220938467017280760L;

    @ApiModelProperty(value = "登录用户名")
    @NotEmpty(message = "登录用户名不能为空")
    @Length(min = 4, max = 16, message = "登录用户名长度不能小于4位也不能超过16位")
    private String username;

    @ApiModelProperty(value = "登录密码")
    @Pattern(regexp = "[0-9A-Za-z!@#$.,]+", message = "登录密码不能包含除数字、字母以及!@#$.,字符外的字符")
    @Length(min = 6, max = 16, message = "登录密码长度不能小于6位也不能超过16位")
    private String password;

    @ApiModelProperty(value = "邮箱地址")
    @Email(message = "邮箱地址不符合规范")
    private String email;

    @Override
    public MemberRegisterDto clone() {
        try {
            return (MemberRegisterDto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new StarrismException(ResultCode.FAILED, e);
        }
    }
}
