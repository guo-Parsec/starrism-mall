package org.starrism.mall.common.pools;

/**
 * <p>数据字典常量池</p>
 *
 * @author hedwing
 * @since 2022/9/2
 **/
public interface DictPool {
    /**
     * 字典类别码 - 性别
     */
    String DICT_CATEGORY_SEX = "SEX";

    /**
     * 字典类别码 - 启用状态
     */
    String DICT_CATEGORY_ENABLE_STATUS = "ENABLE_STATUS";

    /**
     * 字典类别码 - 显示状态
     */
    String DICT_CATEGORY_VISIBLE_STATUS = "VISIBLE_STATUS";

    /**
     * 字典类别码 - 账户锁定状态
     */
    String DICT_CATEGORY_ACCOUNT_LOCK_STATUS = "ACCOUNT_LOCK_STATUS";
}
