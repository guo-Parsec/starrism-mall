package org.starrism.mall.gateway.exceptions;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.StpUtil;

/**
 * <p>代表会话未能通过客户端权限认证</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public class NotClientException extends SaTokenException {
    private static final long serialVersionUID = 364671992531965014L;
    /**
     * 权限码
     */
    private final String client;

    /**
     * @return 获得具体缺少的权限码
     */
    public String getClient() {
        return client;
    }

    /**
     * 账号类型
     */
    private final String loginType;

    /**
     * 获得账号类型
     *
     * @return 账号类型
     */
    public String getLoginType() {
        return loginType;
    }

    public NotClientException(String client) {
        this(client, StpUtil.stpLogic.loginType);
    }

    public NotClientException(String client, String loginType) {
        super("无此客户端权限：" + client);
        this.client = client;
        this.loginType = loginType;
    }

    /**
     * <h1> 警告：自 v1.30+ 版本起，获取异常权限码由 getCode() 更改为 getPermission()，请及时更换！ </h1>
     *
     * @return 获得权限码
     */
    @Deprecated
    public int getCode() {
        return super.getCode();
    }
}
