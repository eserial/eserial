package hu.elte.eserial.exception;

/**
 * Thrown when parser is interrupted due to getting an invalid json
 */
public class EserialParserMismatchException extends EserialException{

    /**
     * Instantiates a new EserialParserMismatchException with the given message.
     *
     * @param msg json format fail
     */
    public EserialParserMismatchException(String msg) {
        super(String.format("%s", msg));
    }
}
