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
    @Getter
    private BaseRestEnum baseRestEnum;
    @Getter
    private Long code;
    @Getter
    private String message;

    public StarrismException(BaseRestEnum baseRestEnum) {
        super(baseRestEnum.getMessage());
        this.baseRestEnum = baseRestEnum;
        this.message = baseRestEnum.getMessage();
    }

    public StarrismException(String message, Long code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public StarrismException(String message, BaseRestEnum baseRestEnum) {
        super(message);
        this.baseRestEnum = baseRestEnum;
        this.message = message;
    }

    public StarrismException(BaseRestEnum baseRestEnum, Throwable cause) {
        super(cause);
        this.baseRestEnum = baseRestEnum;
        this.message = baseRestEnum.getMessage();
    }

    public StarrismException(String message, Throwable cause, BaseRestEnum baseRestEnum) {
        super(message, cause);
        this.baseRestEnum = baseRestEnum;
        this.message = message;
    }
}
