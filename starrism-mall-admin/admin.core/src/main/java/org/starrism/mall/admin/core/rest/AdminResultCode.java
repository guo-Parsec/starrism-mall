package org.starrism.mall.admin.core.rest;

import lombok.Getter;
import org.starrism.mall.common.enums.BaseRestEnum;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
public enum AdminResultCode implements BaseRestEnum {
    /**
     * 用户已存在
     */
    USER_EXIST(100501L, "用户已存在");

    AdminResultCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private Long code;

    @Getter
    private String message;
}
