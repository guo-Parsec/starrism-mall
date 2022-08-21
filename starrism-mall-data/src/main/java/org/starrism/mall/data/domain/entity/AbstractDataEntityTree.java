package org.starrism.mall.data.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.support.Treeable;
import org.starrism.mall.common.util.CollectionUtil;
import org.starrism.mall.common.util.ObjectUtil;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
@Setter
@Getter
public class AbstractDataEntityTree<T> extends BaseDataEntity implements Treeable<T> {
    private static final long serialVersionUID = -2052737090049573872L;
    /**
     * 上级元素id
     */
    protected Long parentId;

    /**
     * 上级元素
     */
    protected T parent;

    /**
     * 子类元素集合
     */
    protected List<T> children;

    /**
     * <p>是否为叶子节点</p>
     *
     * @return boolean
     * @author hedwing
     * @since 2022/8/20
     */
    @Override
    public boolean isLeaf() {
        return CollectionUtil.isEmpty(children);
    }

    /**
     * <p>是否为根节点</p>
     *
     * @return boolean
     * @author hedwing
     * @since 2022/8/20
     */
    @Override
    public boolean isRoot() {
        return ObjectUtil.isAllNull(parentId, parent);
    }
}
