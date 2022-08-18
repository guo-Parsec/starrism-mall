package org.starrism.mall.common.enums;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public interface BaseRestEnum extends IEnum<Long, String> {
    /**
     * <p>获取提示信息</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/8/13
     */
    String getMessage();

    /**
     * <p>获取值</p>
     *
     * @return C
     * @author hedwing
     * @since 2022/8/13
     */
    default String getValue() {
        return getMessage();
    }
}
