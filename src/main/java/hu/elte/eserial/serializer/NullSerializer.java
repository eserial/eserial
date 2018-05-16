package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;

/**
 * Serializes null values.
 */
public class NullSerializer extends AbstractSerializer {

    /**
     * Stores an object reference for serialization.
     * @param object null to be serialized
     */
    NullSerializer(Object object) {
        super(object);
    }

    /**
     * Returns the string representation of null.
     *
     * @return string representation of null
     */
    @Override
    public String serialize() {
        if (object != null) {
            throw new EserialSerializerMismatchException("null", object.getClass().getSimpleName());
        }

        return "null";
    }
}
