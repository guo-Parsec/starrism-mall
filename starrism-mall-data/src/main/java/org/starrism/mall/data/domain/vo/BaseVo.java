package org.starrism.mall.data.domain.vo;

import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.data.domain.entity.BaseEntity;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public abstract class BaseVo extends BaseEntity implements Domainizable {
    private static final long serialVersionUID = -1255571960577933055L;

    @Override
    public BaseVo clone() throws CloneNotSupportedException {
        return (BaseVo) super.clone();
    }

}