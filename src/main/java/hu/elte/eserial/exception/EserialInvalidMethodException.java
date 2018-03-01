package hu.elte.eserial.exception;

/**
 * Thrown when serialization or deserialization is interrupted due to an invalid getter or setter method.
 */
public class EserialInvalidMethodException extends EserialException {

    /**
     * Instantiates a new EserialInvalidMethodException with the given message.
     *
     * @param message the message of the exception
     */
    public EserialInvalidMethodException(String message) {
        super(message);
    }

    /**
     * Instantiates a new EserialInvalidMethodException with the given message and cause.
     *
     * @param message the message of the exception
     * @param cause the cause of the exception
     */
    public EserialInvalidMethodException(String message, Throwable cause) {
        super(message, cause);
    }
}
