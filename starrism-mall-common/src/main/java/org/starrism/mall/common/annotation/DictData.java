package org.starrism.mall.common.annotation;

import java.lang.annotation.*;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictData {
    String categoryCode() default "";


}
