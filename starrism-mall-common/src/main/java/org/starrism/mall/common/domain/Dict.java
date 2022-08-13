package org.starrism.mall.common.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Setter
@Getter
public class Dict<C, V> {
    private C dictCode;

    private V dictValue;

    public Dict() {
    }

    Dict(C dictCode) {
        this.dictCode = dictCode;
    }

    Dict(C dictCode, V dictValue) {
        this.dictCode = dictCode;
        this.dictValue = dictValue;
    }

    public static <C, V> Dict<C, V> of(C dictCode) {
        return new Dict<C, V>(dictCode);
    }

    public static <C, V> Dict<C, V> of(C dictCode, V dictValue) {
        return new Dict<C, V>(dictCode, dictValue);
    }

}
