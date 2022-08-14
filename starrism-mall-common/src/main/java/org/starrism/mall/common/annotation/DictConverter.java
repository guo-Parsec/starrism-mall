package org.starrism.mall.common.annotation;

import java.lang.annotation.*;

/**
 * <p>可转换注解</p>
 *
 * @author hedwing
 * @since 2022/8/14
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictConverter {
}
