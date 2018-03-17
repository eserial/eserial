package hu.elte.eserial.exception;

/**
 * Thrown when object building is interrupted due to a mismatched builder.
 */
public class EserialBuilderMismatchException extends EserialException {

    /**
     * Instantiates a new EserialBuilderMismatchException with the given message.
     *
     * @param expected name of the expected type
     * @param actual name of the actual type
     */
    public EserialBuilderMismatchException(String expected, String actual) {
        super(String.format("Expected %s got %s", expected, actual));
    }
}
