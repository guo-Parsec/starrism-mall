package org.starrism.mall.common.exceptions;

import lombok.Getter;
import org.starrism.mall.common.enums.BaseRestEnum;

/**
 * <p>全局异常类</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public class StarrismException extends RuntimeException {
    private static final long serialVersionUID = -2465996101244268518L;

    private BaseRestEnum baseRestEnum;

    @Getter
    private Long code;

    public StarrismException(BaseRestEnum baseRestEnum) {
        super(baseRestEnum.getMessage());
        this.baseRestEnum = baseRestEnum;
    }

    public StarrismException(String message, Long code) {
        super(message);
        this.code = code;
    }


    public StarrismException(String message, BaseRestEnum baseRestEnum) {
        super(message);
        this.baseRestEnum = baseRestEnum;
    }

    public StarrismException(BaseRestEnum baseRestEnum, Throwable cause) {
        super(cause);
        this.baseRestEnum = baseRestEnum;
    }

    public StarrismException(String message, Throwable cause, BaseRestEnum baseRestEnum) {
        super(message, cause);
        this.baseRestEnum = baseRestEnum;
    }

    public BaseRestEnum getBaseRestEnum() {
        return baseRestEnum;
    }
}
