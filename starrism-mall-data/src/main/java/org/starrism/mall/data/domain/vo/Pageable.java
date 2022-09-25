package org.starrism.mall.data.domain.vo;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/21
 **/
public interface Pageable<E> {
    /**
     * <p>获取总条数</p>
     *
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/8/21
     */
    Long getTotal();

    /**
     * <p>获取页大小</p>
     *
     * @return java.lang.Integer
     * @author hedwing
     * @since 2022/8/21
     */
    Integer getPageSize();

    /**
     * <p>获取当前页</p>
     *
     * @return java.lang.Integer
     * @author hedwing
     * @since 2022/8/21
     */
    Integer getCurrPage();

    /**
     * <p>获取当前页所有记录</p>
     *
     * @return java.util.List<E>
     * @author hedwing
     * @since 2022/8/21
     */
    List<E> getRecords();

}
