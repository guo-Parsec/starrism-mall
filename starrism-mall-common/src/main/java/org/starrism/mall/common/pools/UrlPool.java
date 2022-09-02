package org.starrism.mall.common.pools;

/**
 * <p>Url常量池</p>
 *
 * @author hedwing
 * @since 2022/9/2
 **/
public interface UrlPool {
    String COMMON_PREFIX = "/v1";

    String BMS_PREFIX = COMMON_PREFIX + "/bms";

    String BMS_USER_PREFIX = BMS_PREFIX + "/user";

    String BMS_ROLE_PREFIX = BMS_PREFIX + "/role";

    String BMS_MENU_PREFIX = BMS_PREFIX + "/menu";
}
