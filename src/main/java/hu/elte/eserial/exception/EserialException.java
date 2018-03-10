package hu.elte.eserial.exception;

/**
 * EserialException is the superclass of those exceptions that can be thrown during
 * serialization or deserialization with Eserial.
 */
public class EserialException extends RuntimeException {

    /**
     * Instantiates a new EserialException with the given message.
     *
     * @param message the message of the exception
     */
    public EserialException(String message) {
        super(message);
    }

    /**
     * Instantiates a new EserialException with the given message and cause.
     *
     * @param message the message of the exception
     * @param cause the cause of the exception
     */
    public EserialException(String message, Throwable cause) {
        super(message, cause);
    }
}
