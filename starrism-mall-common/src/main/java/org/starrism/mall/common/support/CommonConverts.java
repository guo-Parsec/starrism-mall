package org.starrism.mall.common.support;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public class CommonConverts {
    public static <S> Convertible<S, String> toStr() {
        return (source) ->  {
            if (source instanceof String) {
                return (String) source;
            }
            return source.toString();
        };
    }
}
