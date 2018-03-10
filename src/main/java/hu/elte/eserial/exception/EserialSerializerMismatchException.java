package hu.elte.eserial.exception;

/**
 * Thrown when serialization is interrupted due to a mismatched serializer.
 */
public class EserialSerializerMismatchException extends EserialException {

    /**
     * Instantiates a new EserialSerializerMismatchException with the given message.
     *
     * @param expected name of the expected type
     * @param actual name of the actual type
     */
    public EserialSerializerMismatchException(String expected, String actual) {
        super(String.format("Expected %s got %s", expected, actual));
    }
}
