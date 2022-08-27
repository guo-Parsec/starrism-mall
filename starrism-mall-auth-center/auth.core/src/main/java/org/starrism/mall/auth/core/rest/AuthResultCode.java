package org.starrism.mall.auth.core.rest;

import lombok.Getter;
import org.starrism.mall.common.enums.BaseRestEnum;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public enum AuthResultCode implements BaseRestEnum {
    /**
     * 客户端认证失败
     */
    AUTHENTICATION_FAILED(1000, "认证失败"),

    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(1001, "用户名或密码错误"),

    /**
     * 用户已被锁定
     */
    ACCOUNT_LOCKED(1002, "用户已被锁定"),

    /**
     * 无效的token
     */
    INVALID_TOKEN(1003, "无效的token"),

    /**
     * 无权限访问
     */
    NO_PERMISSION(1004, "无权限访问");

    @Getter
    private Long code;
    @Getter
    private String message;

    AuthResultCode(Long code, String value) {
        this.code = code;
        this.message = value;
    }

    AuthResultCode(Integer code, String value) {
        this.code = Long.valueOf(code);
        this.message = value;
    }
}
