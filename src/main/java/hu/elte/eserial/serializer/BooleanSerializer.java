package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import hu.elte.eserial.util.TypeUtils;

/**
 * Serializes booleans.
 */
public class BooleanSerializer extends AbstractSerializer {

    /**
     * Stores an object reference for serialization.
     * @param object boolean to be serialized
     */
    BooleanSerializer(Object object) {
        super(object);
    }

    /**
     * Returns the string representation of the boolean {@code object}.
     *
     * @return string representation of {@code object}
     */
    @Override
    public String serialize() {
        if (!TypeUtils.isBoolean(object.getClass())) {
            throw new EserialSerializerMismatchException(Boolean.class.getSimpleName(), object.getClass().getSimpleName());
        }

        return object.toString();
    }
}
