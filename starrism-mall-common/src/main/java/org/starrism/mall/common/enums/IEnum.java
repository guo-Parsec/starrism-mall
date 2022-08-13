package org.starrism.mall.common.enums;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public interface IEnum<C, V> {
    /**
     * <p>获取码值</p>
     *
     * @return C
     * @author hedwing
     * @since 2022/8/13
     */
    C getCode();

    /**
     * <p>获取值</p>
     *
     * @return C
     * @author hedwing
     * @since 2022/8/13
     */
    V getValue();
}
