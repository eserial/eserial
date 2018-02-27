package hu.elte.eserial.exception;

/**
 * EserialException is the superclass of those exceptions that can be thrown during
 * serialization or deserialization with Eserial.
 */
public class EserialException extends RutimeException {

    public EserialException(String message) {
        super(message);
    }

    public EserialException(String message, Throwable cause) {
        super(message, cause);
    }
}
