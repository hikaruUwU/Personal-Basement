package com.demo.base.util.annotation;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public interface ResourceFinder<T> {
    @Nullable T getEntity(@NotNull final Serializable id);
}
