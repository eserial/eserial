package hu.elte.eserial.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Holds a list of element names which should be included in the serialization/deserialization process.
 */
@Retention(RetentionPolicy.RUNTIME)
@EserialAnnotation(priority = 1000)
public @interface IncludeElements {
    String[] value();
}
