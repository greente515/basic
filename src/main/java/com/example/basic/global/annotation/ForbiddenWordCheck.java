package com.example.basic.global.annotation;

import com.example.basic.local.board.model.ParamsPost;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ForbiddenWordCheck {

    String param() default "paramsPost.content";
    Class<?> checkClazz() default ParamsPost.class;
}
