package org.starrism.mall.common.support;

import org.starrism.mall.common.domain.Domainizable;

import java.util.List;

/**
 * <p>可树状化结构</p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public interface Treeable<T> extends Domainizable {
    /**
     * <p>获取ID</p>
     *
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/8/20
     */
    Long getId();

    /**
     * <p>获取上级ID</p>
     *
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/8/20
     */
    Long getParentId();

    /**
     * <p>设置上级ID</p>
     *
     * @param parentId 上级ID
     * @author hedwing
     * @since 2022/8/20
     */
    void setParentId(Long parentId);

    /**
     * <p>获取上级元素</p>
     *
     * @return T
     * @author hedwing
     * @since 2022/8/20
     */
    T getParent();

    /**
     * <p>设置上级元素</p>
     *
     * @param parent 上级元素
     * @author hedwing
     * @since 2022/8/20
     */
    void setParent(T parent);

    /**
     * <p>获取子类集合</p>
     *
     * @return java.util.List<T>
     * @author hedwing
     * @since 2022/8/20
     */
    List<T> getChildren();

    /**
     * <p>设置子类集合</p>
     *
     * @param children 子类集合
     * @author hedwing
     * @since 2022/8/20
     */
    void setChildren(List<T> children);

    /**
     * <p>是否为叶子节点</p>
     *
     * @return boolean
     * @author hedwing
     * @since 2022/8/20
     */
    boolean isLeaf();

    /**
     * <p>是否为根节点</p>
     *
     * @return boolean
     * @author hedwing
     * @since 2022/8/20
     */
    boolean isRoot();
}
