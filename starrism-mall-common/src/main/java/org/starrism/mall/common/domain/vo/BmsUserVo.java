package org.starrism.mall.common.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.domain.Domainizable;

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

    private String sexName;

    public String getSexName() {
        System.out.println(12121212);
        return sexName;
    }
}
