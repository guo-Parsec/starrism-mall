package org.starrism.mall.common.annotation;

import org.starrism.mall.common.enums.FillStrategy;
import org.starrism.mall.common.enums.FillType;

import java.lang.annotation.*;

/**
 * <p>字段填充</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Fill {
    String value() default "";

    /**
     * 字段填充策略
     * 当选择策略为值类型时value不可为空
     *
     * @return 字段填充策略
     */
    FillStrategy strategy() default FillStrategy.VALUE;

    /**
     * 生成类型
     *
     * @return org.starrism.mall.common.enums.IdType
     */
    FillType fillType() default FillType.AUTO;

}
