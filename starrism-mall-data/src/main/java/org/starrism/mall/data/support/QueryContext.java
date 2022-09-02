package org.starrism.mall.data.support;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.starrism.mall.data.domain.entity.BaseDataEntity;

/**
 * <p>查询上下文</p>
 *
 * @author hedwing
 * @since 2022/9/2
 **/
public class QueryContext<T extends BaseDataEntity, R> extends LambdaQueryWrapper<T> {
    private static final long serialVersionUID = 5033397451882413489L;



}
