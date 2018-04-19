package hu.elte.eserial.exception;

/**
 * Thrown when object building is interrupted due to input type mismatch.
 */
public class EserialInputTypeMismatchException extends EserialException {

    /**
     * Instantiates a new EserialInputTypeMismatchException with the given message.
     *
     * @param expected name of the expected {@link java.lang.reflect.Type}
     * @param actual   name of the actual {@link java.lang.reflect.Type}
     */
    public EserialInputTypeMismatchException(String expected, String actual) {
        super(String.format("Expected %s got %s", expected, actual));
    }

    /**
     * Instantiates a new EserialInputTypeMismatchException with the given message.
     *
     * @param actual name of the actual {@link java.lang.reflect.Type}
     */
    public EserialInputTypeMismatchException(String actual) {
        super(String.format("Expected List, Map is initialized from a list of Maps. Got %s", actual));
    }

    /**
     * Instantiates a new EserialInputTypeMismatchException with the given message and cause.
     *
     * @param message the message of the exception
     * @param cause the cause of the exception
     */
    public EserialInputTypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
