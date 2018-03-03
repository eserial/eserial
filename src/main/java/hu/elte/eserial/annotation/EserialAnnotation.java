package hu.elte.eserial.annotation;

import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;
import hu.elte.eserial.util.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The parent annotation for all annotations influencing the serialization/deserialization process.
 * Without having this on an annotation, it will not be considered.
 */
@Target(ElementType.ANNOTATION_TYPE)
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
    EserialAnnotationType type();
}
