package org.starrism.mall.admin.api.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.annotation.DictData;
import org.starrism.mall.common.domain.Dict;
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

    @DictData(categoryCode = "SEX")
    private Dict<Integer, String> sex;


}
