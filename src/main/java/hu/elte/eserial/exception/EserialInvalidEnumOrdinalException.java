package hu.elte.eserial.exception;

/**
 * Thrown when Enum building is interrupted due to invalid Enum ordinal.
 */
public class EserialInvalidEnumOrdinalException extends EserialException {

    /**
     * Instantiates a new EserialInvalidEnumOrdinalException with the given cause.
     */
    public EserialInvalidEnumOrdinalException() {
        super("Could not initialize Enum with the given ordinal");
    }
}
