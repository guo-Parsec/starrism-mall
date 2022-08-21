package org.starrism.mall.common.support;

import org.springframework.stereotype.Component;
import org.starrism.mall.common.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>基于{@link Treeable}树状结构数据递归方式处理器</p>
 *
 * <p>{@code E}表述为可转化为树结构原始元素</p>
 *
 * <p>{@code K}表述为键类型，支持任意基于Serializable的键类型</p>
 *
 * @author hedwing
 * @since 2022/8/21
 **/
@Component("recursionTreeProcessor")
public class RecursionTreeProcessor<E extends Treeable<E>> implements TreeProcessor<E> {
    /**
     * <p>元素数组转化为树结构数据</p>
     *
     * @param elements 元素数组
     * @param parentId 上级节点主键
     * @return 树结构数据
     */
    @Override
    public List<E> toTree(Collection<E> elements, Long parentId) {
        // 筛选出来顶级目录
        List<E> rootList = elements.stream()
                .filter(element -> Objects.equals(parentId, element.getParentId()))
                .collect(Collectors.toList());
        // 从数据集合中剔除顶层目录，减少后续遍历次数，加快速度
        elements.removeAll(rootList);
        if (!CollectionUtil.isEmpty(rootList)) {
            for (E element : rootList) {
                setChildren(elements, element);
            }
            return rootList;
        }
        return new ArrayList<>(elements);
    }

    private void setChildren(Collection<E> elements, E parent) {
        List<E> collect = elements.stream()
                .filter(element -> isChild(parent, element))
                .collect(Collectors.toList());
        parent.setChildren(collect);
        elements.removeAll(collect);
        if (!CollectionUtil.isEmpty(collect)) {
            for (E element : collect) {
                setChildren(elements, element);
            }
        }
    }

    /**
     * <p>判断当前对象是不是父级对象的子级</p>
     *
     * @param parent  父级对象
     * @param element 当前对象
     * @return 如果当前对象 {@code element} 是父级对象 {@code parent} 的子级则返回true,否则返回false
     */
    private boolean isChild(E parent, E element) {
        return element.getParentId().equals(parent.getId());
    }
}
