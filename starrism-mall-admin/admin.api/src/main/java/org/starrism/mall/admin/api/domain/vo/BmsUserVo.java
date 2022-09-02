package org.starrism.mall.admin.api.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.base.context.DictContext;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.pools.DictPool;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Setter
@Getter
public class BmsUserVo implements Domainizable {
    private static final long serialVersionUID = 8539088195437264237L;
    private Long id;

    private String username;

    private String nickname;

    private String email;

    private String phoneNumber;

    private String avatarUrl;

    private Integer sex;

    private String sexName;

    private Integer enableStatus;

    private String enableStatusName;

    public String getSexName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_SEX, this.sex);
    }

    public String getEnableStatusName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_ENABLE_STATUS, this.enableStatus);
    }
}
