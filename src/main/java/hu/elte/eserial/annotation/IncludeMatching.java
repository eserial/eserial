package hu.elte.eserial.annotation;

import hu.elte.eserial.annotation.enumeration.IncludeMatcher;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Holds a list of constraints which should <b>all</b> be true for an element
 * to be included in the serialization/deserialization process.
 */
@Retention(RetentionPolicy.RUNTIME)
@EserialAnnotation(priority = 1000)
public @interface IncludeMatching {
    IncludeMatcher[] value();
}
