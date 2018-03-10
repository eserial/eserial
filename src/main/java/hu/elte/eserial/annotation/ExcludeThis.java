package hu.elte.eserial.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;

/**
 * Indicates that an element should be excluded from the serialization/deserialization process.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@EserialAnnotation(priority = 3000, type = EserialAnnotationType.Inclusion)
public @interface ExcludeThis { }
