package hu.elte.eserial.exception;

/**
 * Thrown when object building is interrupted due to failed instantiation.
 */
public class EserialInstantiationException extends EserialException {

    /**
     * Instantiates a new EserialInstantiationException with the given message.
     *
     * @param message is the error message
     */
    public EserialInstantiationException(String message) {
        super(message);
    }
}
