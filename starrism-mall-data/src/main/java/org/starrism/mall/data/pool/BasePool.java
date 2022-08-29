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
    Integer ENABLE = 0;

    Integer DISABLE = 1;
}
