package com.example.fa_blog_study.common.aop;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    String module() default "";
    String operator() default "";
}
