package hu.elte.eserial.annotation;

import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Holds a list of element names which should be included in the serialization/deserialization process.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EserialAnnotation(priority = 2000, type = EserialAnnotationType.INCLUSION)
public @interface IncludeElements {
    String[] value();
}
