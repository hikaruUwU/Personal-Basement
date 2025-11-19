package com.demo.base.util;
//Do not use in executor.
public class CurrentChain {
    private final InheritableThreadLocal<Object> value = new InheritableThreadLocal<>();

    private CurrentChain() {
    }

    public void set(Object value) {
        this.value.set(value);
    }

    public Object get() {
        return value.get();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> cast) {
        return (T) value.get();
    }

    public void remove() {
        value.remove();
    }

    public Object getAndRemove() {
        Object o = get();
        remove();
        return o;
    }

    public <T> T getAndRemove(Class<T> cast) {
        T t = get(cast);
        remove();
        return t;
    }
}
