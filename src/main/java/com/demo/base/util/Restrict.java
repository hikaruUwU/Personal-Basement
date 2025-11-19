package com.demo.base.util;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented

//Use to block those requests have no session.

public @interface Restrict {
    String hint() default "";
}



