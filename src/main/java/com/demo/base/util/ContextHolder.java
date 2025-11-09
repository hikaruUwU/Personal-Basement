package com.demo.base.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ContextHolder {
    private final ThreadLocal<Object> current = new ThreadLocal<>();

    public void set(Object rec) {
        current.set(rec);
    }

    public Object get() {
        return current.get();
    }

    public void remove() {
        current.remove();
    }

    public Object finalGet() {
        Object o = current.get();
        current.remove();
        return o;
    }
}