package org.starrism.mall.common.annotation;

import org.starrism.mall.common.enums.FillType;

import java.lang.annotation.*;

/**
 * <p>主键标识注解</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    /**
     * id生成类型
     *
     * @return org.starrism.mall.common.enums.IdType
     */
    FillType idType() default FillType.ALWAYS;
}
