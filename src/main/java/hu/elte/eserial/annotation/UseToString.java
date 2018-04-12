package hu.elte.eserial.annotation;

import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicated that the annotated element's {@code toString()} method should be used for serializing.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EserialAnnotation(type = EserialAnnotationType.FORMATTING)
public @interface UseToString { }
