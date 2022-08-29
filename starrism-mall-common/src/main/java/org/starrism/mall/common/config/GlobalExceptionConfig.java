package org.starrism.mall.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.starrism.mall.common.enums.BaseRestEnum;
import org.starrism.mall.common.exceptions.StarrismException;
import org.starrism.mall.common.rest.CommonResult;

/**
 * <p>全局异常配置</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionConfig {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    /**
     * 参数校验失败异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResult<Void> validException(MethodArgumentNotValidException exception) {
        ObjectError objectError = exception.getBindingResult().getAllErrors().get(0);
        log.error("valid Param failed", exception);
        return CommonResult.validateFailed(objectError.getDefaultMessage());
    }

    /**
     * Starrism异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({StarrismException.class})
    public CommonResult<Void> starrismExceptionHandler(StarrismException exception) {
        log.error("The application run starrismException", exception);
        BaseRestEnum baseRestEnum = exception.getBaseRestEnum();
        if (baseRestEnum == null) {
            return CommonResult.failed(exception.getMessage(), exception.getCode());
        }
        return CommonResult.failed(exception.getBaseRestEnum());
    }

    /**
     * 异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({Exception.class})
    public CommonResult<Void> defaultExceptionHandler(Exception exception) {
        log.error("The application run exception", exception);
        return CommonResult.failed();
    }


}
