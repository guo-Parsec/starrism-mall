package org.starrism.mall.data.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.starrism.mall.common.annotation.Fill;
import org.starrism.mall.common.enums.FillStrategy;
import org.starrism.mall.common.enums.FillType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>基础数据实体类</p>
 *
 * @author hedwing
 * @since 2022/8/12
 **/
@Setter
@Getter
@ToString
public abstract class BaseDataEntity extends BaseEntity {
    private static final long serialVersionUID = -2490534118894809930L;

    /**
     * 创建时间
     */
    @Fill(strategy = FillStrategy.DATETIME, fillType = FillType.ALWAYS)
    protected LocalDateTime addTime;

    /**
     * 创建人
     */
    @Fill(value = "admin", strategy = FillStrategy.VALUE, fillType = FillType.AUTO)
    protected String addName;

    /**
     * 修改时间
     */
    @Fill(strategy = FillStrategy.DATETIME, fillType = FillType.ALWAYS)
    protected LocalDateTime modifyTime;

    /**
     * 修改人
     */
    @Fill(value = "admin", strategy = FillStrategy.VALUE, fillType = FillType.AUTO)
    protected String modifyName;

    /**
     * 启用状态(数据字典 0-启用 1-禁用)
     */
    @Fill(value = "0", strategy = FillStrategy.VALUE, fillType = FillType.AUTO)
    protected Integer enableStatus;

    public BaseDataEntity() {

    }

    public BaseDataEntity(Long id) {
        super(id);
    }

    public BaseDataEntity(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BaseDataEntity that = (BaseDataEntity) o;
        return Objects.equals(modifyTime, that.modifyTime) && Objects.equals(modifyName, that.modifyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), modifyTime, modifyName);
    }


    @Override
    public BaseDataEntity clone() throws CloneNotSupportedException {
        return (BaseDataEntity) super.clone();
    }
}
