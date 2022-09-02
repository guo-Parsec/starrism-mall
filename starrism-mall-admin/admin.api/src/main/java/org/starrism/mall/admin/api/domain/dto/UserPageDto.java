package org.starrism.mall.admin.api.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.data.domain.param.BasePageDto;

import java.util.Set;

/**
 * <p>用户分页数据</p>
 *
 * @author hedwing
 * @since 2022/9/2
 **/
@Setter
@Getter
public class UserPageDto extends BasePageDto {
    private static final long serialVersionUID = 4201973469347249541L;

    @ApiModelProperty(value = "登录用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱地址")
    public String email;

    @ApiModelProperty(value = "手机号吗")
    private String phoneNumber;

    @ApiModelProperty(value = "性别信息")
    private Set<Integer> sex;

    @ApiModelProperty(value = "启用状态")
    private Set<Integer> enableStatus;
}
