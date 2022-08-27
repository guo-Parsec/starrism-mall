package org.starrism.mall.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ExceptionHandler({StarrismException.class})
    public CommonResult<Void> starrismExceptionHandler(StarrismException exception) {
        log.error("The application run starrismException", exception);
        return CommonResult.failed(exception.getBaseRestEnum());
    }

    @ExceptionHandler({Exception.class})
    public CommonResult<Void> defaultExceptionHandler(Exception exception) {
        log.error("The application run exception", exception);
        return CommonResult.failed();
    }


}
