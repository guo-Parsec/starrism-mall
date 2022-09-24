package org.starrism.mall.base.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.base.context.DictContext;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.pools.DictPool;
import org.starrism.mall.data.pool.BasePool;

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

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "角色列表")
    private Set<String> roles;

    @ApiModelProperty(value = "权限列表")
    private Set<String> permissions;

    @ApiModelProperty(value = "性别字典值")
    private Integer sex;

    @ApiModelProperty(value = "性别")
    private String sexName;

    @ApiModelProperty(value = "状态字典值")
    private Integer enableStatus;

    @ApiModelProperty(value = "状态")
    private String enableStatusName;

    public String getSexName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_SEX, this.sex);
    }

    public String getEnableStatusName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_ENABLE_STATUS, this.enableStatus);
    }

    @Override
    public CoreUser clone() throws CloneNotSupportedException {
        return (CoreUser) super.clone();
    }

    /**
     * <p>是否锁定判断</p>
     *
     * @return boolean 如果用户被锁定则返回true, 否则返回false
     * @author hedwing
     * @since 2022/9/24
     */
    public boolean isLock() {
        return BasePool.LOCK_USER.equals(this.enableStatus);
    }

    /**
     * <p>是否禁用判断</p>
     *
     * @return boolean 如果用户被禁用则返回true, 否则返回false
     * @author hedwing
     * @since 2022/9/24
     */
    public boolean isDisabled() {
        return BasePool.DISABLE.equals(this.enableStatus);
    }

    /**
     * <p>是否启用判断</p>
     *
     * @return boolean 如果用户被启用则返回true, 否则返回false
     * @author hedwing
     * @since 2022/9/24
     */
    public boolean isEnable() {
        return BasePool.ENABLE.equals(this.enableStatus);
    }


}
