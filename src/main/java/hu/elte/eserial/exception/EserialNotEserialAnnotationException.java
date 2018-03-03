package hu.elte.eserial.exception;

import hu.elte.eserial.annotation.EserialAnnotation;

/**
 * Thrown when annotation processing is interrupted due to getting a non-{@link EserialAnnotation}
 * where one was expected.
 */
public class EserialNotEserialAnnotationException extends EserialException {

    public EserialNotEserialAnnotationException(String annotationType) {
        super(String.format("Not an EserialAnnotation: %s.", annotationType));
    }
}
