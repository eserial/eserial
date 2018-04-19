package hu.elte.eserial.exception;

import hu.elte.eserial.annotation.enumeration.EnumeratedFormat;

/**
 * Thrown when Enum building is interrupted due to invalid Enum ordinal or name.
 */
public class EserialInvalidEnumException extends EserialException {

    /**
     * Instantiates a new EserialInvalidEnumException with a message generated from the given {@link EnumeratedFormat}.
     */
    public EserialInvalidEnumException(EnumeratedFormat format) {
        super("Could not initialize Enum with the given " + format.name().toLowerCase());
    }
}
