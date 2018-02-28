package hu.elte.eserial.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import hu.elte.eserial.annotation.enumeration.EnumeratedFormat;
import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;

/**
 * Decides how the annotated enum should be serialized/deserialized.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@EserialAnnotation(type = EserialAnnotationType.Formatting)
public @interface Enumerated {
  EnumeratedFormat value();
}
