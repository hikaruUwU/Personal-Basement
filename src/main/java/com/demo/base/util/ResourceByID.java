package com.demo.base.util;

import com.demo.base.repository.ResourceFinder;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented

//Use on controller's pathVariable,

public @interface ResourceByID {
    Class<? extends ResourceFinder<?>> finder();
    String pathVariable();
}
