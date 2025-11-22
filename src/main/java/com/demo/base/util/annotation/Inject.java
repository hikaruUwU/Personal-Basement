package com.demo.base.util.annotation;

import java.lang.annotation.*;

//Use on controller's target,only support path variable

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inject {
    Class<? extends ResourceFinder<?>> finder();

}
