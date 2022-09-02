package org.starrism.mall.common.config;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.starrism.mall.common.enums.BaseRestEnum;
import org.starrism.mall.common.exceptions.StarrismException;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
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
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(GlobalExceptionConfig.class);

    /**
     * 参数校验失败异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResult<Void> validException(MethodArgumentNotValidException exception) {
        ObjectError objectError = exception.getBindingResult().getAllErrors().get(0);
        LOGGER.error("valid Param failed", exception);
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
        LOGGER.error("The application run starrismException", exception);
        BaseRestEnum baseRestEnum = exception.getBaseRestEnum();
        if (baseRestEnum == null) {
            return CommonResult.failed(exception.getMessage(), exception.getCode());
        }
        return CommonResult.failed(exception.getBaseRestEnum(), exception.getMessage());
    }

    /**
     * 异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({Exception.class})
    public CommonResult<Void> defaultExceptionHandler(Exception exception) {
        LOGGER.error("The application run exception", exception);
        return CommonResult.failed();
    }


}
