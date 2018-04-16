package hu.elte.eserial.exception;

/**
 * Thrown when parser is interrupted due to getting an invalid json
 */
public class EserialInvalidJsonException extends EserialException{

    /**
     * Instantiates a new EserialInvalidJsonException with the given message.
     *
     * @param msg json format fail
     */
    public EserialInvalidJsonException(String msg) {
        super(String.format("%s", msg));
    }
}
