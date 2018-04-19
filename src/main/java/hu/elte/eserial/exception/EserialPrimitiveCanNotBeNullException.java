package hu.elte.eserial.exception;

/**
 * Thrown when object building is interrupted due to null value given to a primitive type.
 */
public class EserialPrimitiveCanNotBeNullException extends EserialException {

    /**
     * Instantiates a new EserialPrimitiveCanNotBeNullException with the given message.
     * @param type is the primitive type
     */
    public EserialPrimitiveCanNotBeNullException(String type) {
        super(String.format("Type %s cannot be null", type));
    }
}
