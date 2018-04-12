package hu.elte.eserial.annotation;

import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Holds a list of constraints which should <b>all</b> be true for an element
 * to be included in the serialization/deserialization process.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EserialAnnotation(priority = 1000, type = EserialAnnotationType.INCLUSION)
public @interface IncludeMatching {
    IncludeMatcher[] value();
}
