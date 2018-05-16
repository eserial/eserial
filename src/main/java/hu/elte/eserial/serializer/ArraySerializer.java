package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.util.List;
import java.util.StringJoiner;

/**
 * Serializes lists.
 */
public class ArraySerializer extends AbstractSerializer {

    /**
     * Stores an object reference for serialization.
     * @param object list to be serialized
     */
    ArraySerializer(Object object) {
        super(object);
    }

    /**
     * Returns the string representation of the list {@code object}.
     *
     * @return string representation of {@code object}
     */
    @Override
    public String serialize() {
        if (!TypeUtils.isAssignableFrom(object.getClass(), List.class)) {
            throw new EserialSerializerMismatchException(List.class.getSimpleName(), object.getClass().getSimpleName());
        }

        List list = (List) object;
        StringJoiner joiner = new StringJoiner(",");

        for (Object element : list) {
            joiner.add(SerializerFactory.create(element).serialize());
        }

        return "[" + joiner.toString() + "]";
    }
}
