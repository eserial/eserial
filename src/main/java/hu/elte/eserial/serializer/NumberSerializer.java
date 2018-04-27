package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import hu.elte.eserial.util.TypeUtils;

/**
 * Serializes numbers.
 */
public class NumberSerializer extends AbstractSerializer {

    /**
     * Stores an object reference for serialization.
     * @param object number to be serialized
     */
    NumberSerializer(Object object) {
        super(object);
    }

    /**
     * Returns the string representation of the number {@code object}.
     *
     * @return string representation of {@code object}
     */
    @Override
    public String serialize() {
        if (!TypeUtils.isNumber(object.getClass())) {
            throw new EserialSerializerMismatchException(Number.class.getSimpleName(), object.getClass().getSimpleName());
        }

        return object.toString();
    }
}
