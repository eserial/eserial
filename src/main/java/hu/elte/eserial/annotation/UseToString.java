package hu.elte.eserial.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;

/**
 * Indicated that the annotated element's {@code toString()} method should be used for serializing.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@EserialAnnotation(type = EserialAnnotationType.Formatting)
public @interface UseToString { }
