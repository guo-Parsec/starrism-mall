package org.starrism.mall.common.rest;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starrism.mall.common.enums.BaseRestEnum;

/**
 * <p>结果返回集</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Setter
@Getter
public class CommonResult<T> {
    private static final Logger log = LoggerFactory.getLogger(CommonResult.class);
    private long code;

    private String message;

    private T data;

    protected CommonResult() {

    }

    CommonResult(long code) {
        this.code = code;
    }

    CommonResult(long code, T data) {
        this.code = code;
        this.data = data;
    }

    CommonResult(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(BaseRestEnum baseRestEnum) {
        this.code = baseRestEnum.getCode();
        this.message = baseRestEnum.getMessage();
    }

    public CommonResult(BaseRestEnum baseRestEnum, T data) {
        this.code = baseRestEnum.getCode();
        this.message = baseRestEnum.getMessage();
        this.data = data;
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResultCode.SUCCESS);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS, data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return new CommonResult<T>(ResultCode.FAILED);
    }

    /**
     * 失败返回结果
     *
     * @param baseRestEnum 错误码
     */
    public static <T> CommonResult<T> failed(BaseRestEnum baseRestEnum) {
        return new CommonResult<T>(baseRestEnum);
    }

    /**
     * 失败返回结果
     *
     * @param baseRestEnum 错误码
     * @param message      错误信息
     */
    public static <T> CommonResult<T> failed(BaseRestEnum baseRestEnum, String message) {
        return new CommonResult<T>(baseRestEnum.getCode(), message, null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED, data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN, data);
    }

    /**
     * <p>获取成功请求数据</p>
     *
     * @param commonResult 统一响应格式
     * @return T
     * @author hedwing
     * @since 2022/8/27
     */
    public static <T> T getSuccessData(CommonResult<T> commonResult) {
        if (commonResult == null) {
            log.error("请求结果集为空，可能为请求错误");
            throw new RuntimeException("The request result set is empty, possibly a request error");
        }
        long resultCode = commonResult.getCode();
        if (!ResultCode.SUCCESS.getCode().equals(resultCode)) {
            log.error("请求信息码为{}，可能为请求失败", resultCode);
            throw new RuntimeException("The request info code is not success, possibly a request error");
        }
        return commonResult.getData();
    }
}
