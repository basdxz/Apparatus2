package com.github.basdxz.apparatus.common.loader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisteredLoader {
    String registryName();

    Class<?> paraThingClass();
}
