package org.starrism.mall.common.rest;

import lombok.Getter;
import org.starrism.mall.common.enums.BaseRestEnum;

/**
 * <p>数据码枚举</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public enum ResultCode implements BaseRestEnum {
    /**
     * 信息码
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    @Getter
    private Long code;
    @Getter
    private String message;

    ResultCode(Long code, String value) {
        this.code = code;
        this.message = value;
    }

    ResultCode(Integer code, String value) {
        this.code = Long.valueOf(code);
        this.message = value;
    }
}
