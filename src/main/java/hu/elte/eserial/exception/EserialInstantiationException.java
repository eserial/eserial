package hu.elte.eserial.exception;

/**
 * Thrown when object building is interrupted due to failed instantiation.
 */
public class EserialInstantiationException extends EserialException {

    /**
     * Instantiates a new EserialInstantationException with the given message and cause.
     *
     * @param message the message of the exception
     * @param cause the cause of the exception
     */
    public EserialInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
