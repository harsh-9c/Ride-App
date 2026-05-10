package com.project.ridebooking.rideApp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {
    String action();              // CREATE, UPDATE, DELETE, etc.
    String entityType();          // USER, RIDE, DRIVER, etc.
    String description() default "";
    boolean captureResult() default true;
}