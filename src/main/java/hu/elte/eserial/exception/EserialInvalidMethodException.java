package hu.elte.eserial.exception;

/**
 * Thrown when serialization or deserialization is interrupted due to an invalid getter or setter method.
 */
public class EserialInvalidMethodException extends EserialException {

    public EserialInvalidMethodException(String message) {
        super(message);
    }

    public EserialInvalidMethodException(String message, Throwable cause) {
        super(message, cause);
    }
}
