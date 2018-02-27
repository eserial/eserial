package hu.elte.eserial.exception;

/**
 * Thrown when serialization is interrupted due to a mismatched mapper.
 */
public class EserialMapperMismatchException extends EserialException {

    public EserialMapperMismatchException(String expected, String actual) {
        super(String.format("Expected %s got %s", expected, actual));
    }
}
