package hu.elte.eserial.exception;

/**
 * Thrown when serialization is interrupted due to a mismatched mapper.
 */
public class EserialMapperMismatchException extends EserialException {

    public EserialMapperMismatchException(Class<?> actual) {
        super(actual.toString());
    }
}
