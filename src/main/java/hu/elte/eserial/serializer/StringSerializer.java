package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import hu.elte.eserial.util.StringUtils;
import hu.elte.eserial.util.TypeUtils;

/**
 * Serializes strings.
 */
public class StringSerializer extends AbstractSerializer {

    /**
     * Stores an object reference for serialization.
     * @param object string to be serialized
     */
    StringSerializer(Object object) {
        super(object);
    }

    /**
     * Returns the string representation of the string {@code object}.
     *
     * @return string representation of {@code object}
     */
    @Override
    String serialize() {
        if (!TypeUtils.isString(object.getClass()) && !TypeUtils.isCharacter(object.getClass())) {
            throw new EserialSerializerMismatchException("String or Character", object.getClass().getSimpleName());
        }

        return "\"" + StringUtils.escape(object.toString()) + "\"";
    }
}
