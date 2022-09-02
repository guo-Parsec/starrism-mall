package org.starrism.mall.common.pools;

/**
 * <p>参数常量池</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
public interface ParamPool {
    /**
     * 默认角色参数key
     */
    String DEFAULT_ROLE_KEY = "DEFAULT_ROLE";

    /**
     * 默认性别参数key
     */
    String DEFAULT_SEX_KEY = "DEFAULT_SEX";

    /**
     * 默认操作用户参数key
     */
    String DEFAULT_OPERATE_USER_KEY = "DEFAULT_OPERATE_USER";

    /**
     * 密码输错锁定开关参数key ON-开启密码输入错误后可能会锁定账户的开关 OFF-关闭
     */
    String PWD_WRONG_LOCK_SWITCH_KEY = "PWD_WRONG_LOCK_SWITCH";

    /**
     * 密码输错锁定账户时间参数key 在指定小时数内输错密码指定次数后将会被锁定
     */
    String PWD_WRONG_LOCK_HOURS_KEY = "PWD_WRONG_LOCK_HOURS";

    /**
     * 最大容忍密码输错次数参数key 输错密码超过指定次数后会被锁定
     */
    String MAX_TOLERANCE_PWD_WRONG_KEY = "MAX_TOLERANCE_PWD_WRONG";

    /**
     * 密码输错超过期限解锁账户时间参数key 密码输入错误锁定账户后指定小时后才可以自动解锁
     */
    String PWD_WRONG_UNLOCK_HOURS_KEY = "PWD_WRONG_UNLOCK_HOURS";
}
