package hu.elte.eserial.exception;

/**
 * Thrown when object building is interrupted due to the lack of default constructor.
 */
public class EserialNoDefaultConstructorException extends EserialException{

    /**
     * Instantiates a new EserialNoDefaultConstructorException with the given cause.
     *
     * @param cause the cause of the exception
     */
    public EserialNoDefaultConstructorException(Throwable cause) {
        super("Could not find default constructor", cause);
    }
}
