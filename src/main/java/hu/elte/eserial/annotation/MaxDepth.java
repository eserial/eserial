package hu.elte.eserial.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Holds the maximum depth of the map to be generated from a class.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@EserialAnnotation(priority = Integer.MAX_VALUE)
public @interface MaxDepth {
    int value();
}
