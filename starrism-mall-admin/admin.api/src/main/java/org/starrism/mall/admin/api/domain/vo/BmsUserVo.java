package org.starrism.mall.admin.api.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.data.service.client.DictClient;

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

    public String getSexName() {
        return DictClient.convert("SEX", this.sex);
    }
}
