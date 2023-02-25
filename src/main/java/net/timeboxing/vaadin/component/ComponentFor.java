package net.timeboxing.vaadin.component;


import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for
 */
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ComponentFor {

    Class<?> forClass();

    ComponentPurpose purpose() default ComponentPurpose.DEFAULT;

    String purposeType() default "";

    String purposeValue() default "";
}
