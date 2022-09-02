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
     * 删除状态
     */
    Integer LOCK = 3;

    String DEFAULT_DELIMITER = ",";
}
