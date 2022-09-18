package org.starrism.mall.data.pool;

import org.apache.ibatis.type.Alias;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@Alias("BasePool")
public interface BasePool {
    /**
     * 启用状态
     */
    Integer ENABLE = 0;

    /**
     * 禁用状态
     */
    Integer DISABLE = 1;

    /**
     * 删除状态
     */
    Integer DELETE = 2;

    /**
     * 锁定状态
     */
    Integer LOCK_USER = 3;

    String DEFAULT_DELIMITER = ",";

    /**
     * 锁定
     */
    Integer LOCK_ACCOUNT = 0;

    /**
     * 已解锁
     */
    Integer UNLOCK_ACCOUNT = 1;

    /**
     * 自动解锁
     */
    Integer AUTO_UNLOCK_ACCOUNT = 2;

    /**
     * 手动解锁
     */
    Integer MANUAL_UNLOCK_ACCOUNT = 3;
}
