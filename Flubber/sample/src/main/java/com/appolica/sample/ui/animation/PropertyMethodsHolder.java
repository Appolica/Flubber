package com.appolica.sample.ui.animation;

import com.annimon.stream.function.FunctionalInterface;

public class PropertyMethodsHolder<T> {

    private final Getter<T> getter;
    private final Setter<T> setter;

    public PropertyMethodsHolder(Getter<T> getter, Setter<T> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    static<T> PropertyMethodsHolder<T> fields(Getter<T> getter, Setter<T> setter) {
        return new PropertyMethodsHolder<>(getter, setter);
    }

    public Getter<T> getter() {
        return getter;
    }

    public Setter<T> setter() {
        return setter;
    }

    @FunctionalInterface
    public interface Setter<T> {
        void set(T value);
    }

    @FunctionalInterface
    public interface Getter<T> {
        T get();
    }
}
