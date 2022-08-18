package org.starrism.mall.data.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.annotation.Id;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.enums.FillType;

import java.util.Objects;

/**
 * <p>基础实体类</p>
 *
 * @author hedwing
 * @since 2022/8/12
 **/
@Setter
@Getter
public abstract class BaseEntity implements Domainizable {
    private static final long serialVersionUID = 1425584601845122904L;

    /**
     * 主键ID
     */
    @Id(idType = FillType.AUTO)
    protected Long id;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity(String id) {
        this.id = Long.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }

    @Override
    public BaseEntity clone() throws CloneNotSupportedException {
        return (BaseEntity) super.clone();
    }
}
