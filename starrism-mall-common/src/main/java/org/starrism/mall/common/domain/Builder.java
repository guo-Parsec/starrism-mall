package org.starrism.mall.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * <p>通用构造器</p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public class Builder<T> {
    private final Supplier<T> instance;

    private final List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instance) {
        this.instance = instance;
    }

    public static <T> Builder<T> of(Supplier<T> instance) {
        return new Builder<>(instance);
    }

    public <U> Builder<T> with(BiConsumer<T, U> consumer, U u) {
        modifiers.add(instance -> consumer.accept(instance, u));
        return this;
    }

    public T build() {
        T value = instance.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }
}
