package org.starrism.mall.auth.core.exception;

import org.starrism.mall.auth.core.rest.AuthResultCode;
import org.starrism.mall.common.exceptions.StarrismException;

/**
 * <p>验证异常类</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public class AuthException extends StarrismException {
    private static final long serialVersionUID = 5333187782196229329L;

    private AuthResultCode authResultCode;

    public AuthException(AuthResultCode authResultCode) {
        super(authResultCode);
        this.authResultCode = authResultCode;
    }

    public AuthException(String message, AuthResultCode authResultCode) {
        super(message, authResultCode);
        this.authResultCode = AuthResultCode.AUTHENTICATION_FAILED;
    }

    public AuthException(String message, Throwable cause, AuthResultCode authResultCode) {
        super(message, cause, authResultCode);
        this.authResultCode = AuthResultCode.AUTHENTICATION_FAILED;
    }

    public AuthResultCode getAuthRestStatus() {
        return authResultCode;
    }

    public void setAuthRestStatus(AuthResultCode authRestStatus) {
        this.authResultCode = authRestStatus;
    }
}
