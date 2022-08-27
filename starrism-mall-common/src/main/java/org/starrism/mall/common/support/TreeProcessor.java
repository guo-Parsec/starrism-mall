package org.starrism.mall.common.support;

import java.util.Collection;
import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/21
 **/
public interface TreeProcessor<E extends Treeable<E>> {
    /**
     * <p>元素数组转化为树结构数据</p>
     *
     * @param elements     元素数组
     * @param parentId     上级节点主键
     * @return 树结构数据
     */
    List<E> toTree(Collection<E> elements, Long parentId);
}
