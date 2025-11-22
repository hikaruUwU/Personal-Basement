package com.demo.base.util.annotation;

import jakarta.annotation.Nonnull;

import java.lang.annotation.*;

//Use on controller's target,only support path variable

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Nonnull
public @interface ResourceInjector {
    Class<? extends ResourceFinder<?>> finder();

    String target();
}
