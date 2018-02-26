package hu.elte.eserial.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import hu.elte.eserial.util.AnnotationUtils;

/**
 * The parent annotation for all annotations influencing the serialization/deserialization process.
 * Without having this on an annotation, it will not be considered.<br>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface EserialAnnotation {
    /**
     * Higher values indicate higher priority, meaning the annotation will be processed sooner
     * than one with a lower priority.<br>
     * Ordering two annotations with the same priority results in undefined behavior.<br>
     * The default priority is 0.
     * @return the priority of the annotation
     *
     * @see AnnotationUtils#compare(Annotation, Annotation)
     */
    int priority() default 0;
}
