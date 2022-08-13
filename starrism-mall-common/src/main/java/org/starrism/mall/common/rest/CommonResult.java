package org.starrism.mall.common.rest;

import lombok.Getter;
import lombok.Setter;
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
     *
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
}