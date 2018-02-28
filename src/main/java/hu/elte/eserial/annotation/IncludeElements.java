package hu.elte.eserial.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;

/**
 * Holds a list of element names which should be included in the serialization/deserialization process.
 */
@Retention(RetentionPolicy.RUNTIME)
@EserialAnnotation(priority = 1000, type = EserialAnnotationType.Inclusion)
public @interface IncludeElements {
    String[] value();
}
