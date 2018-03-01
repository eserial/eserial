package hu.elte.eserial.exception;

/**
 * Thrown when serialization is interrupted due to a mismatched mapper.
 */
public class EserialMapperMismatchException extends EserialException {

    /**
     * Instantiates a new EserialMapperMismatchException with the given message.
     *
     * @param expected name of the expected type
     * @param actual name of the actual type
     */
    public EserialMapperMismatchException(String expected, String actual) {
        super(String.format("Expected %s got %s", expected, actual));
    }
}
