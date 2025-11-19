package com.demo.base.repository;

import java.io.Serializable;

public interface ResourceFinder<T> {
    T get(Serializable id);
}
