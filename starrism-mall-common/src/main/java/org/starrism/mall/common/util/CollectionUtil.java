package org.starrism.mall.common.util;

import java.util.Collection;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public class CollectionUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
