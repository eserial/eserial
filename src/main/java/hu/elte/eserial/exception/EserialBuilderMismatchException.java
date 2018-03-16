package hu.elte.eserial.exception;

public class EserialBuilderMismatchException extends EserialException {

    public EserialBuilderMismatchException(String expected, String actual) {
        super(String.format("Expected %s got %s", expected, actual));
    }
}
