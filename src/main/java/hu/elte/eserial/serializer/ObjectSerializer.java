package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.util.Map;
import java.util.StringJoiner;

/**
 * Serializes maps.
 */
public class ObjectSerializer extends AbstractSerializer {

    /**
     * Stores an object reference for serialization.
     * @param object map to be serialized
     */
    ObjectSerializer(Object object) {
        super(object);
    }

    /**
     * Returns the string representation of the map {@code object}.
     *
     * @return string representation of {@code object}
     */
    @Override
    String serialize() {
        if (!TypeUtils.isMap(object.getClass())) {
            throw new EserialSerializerMismatchException(Map.class.getSimpleName(), object.getClass().getSimpleName());
        }

        Map map = (Map) object;

        StringJoiner joiner = new StringJoiner(",");

        for (Object entryObject : map.entrySet()) {
            Map.Entry entry = (Map.Entry) entryObject;

            String serializedElement =
                    SerializerFactory.create(entry.getKey()).serialize()
                    + ":" +
                    SerializerFactory.create(entry.getValue()).serialize();

            joiner.add(serializedElement);
        }

        return "{" + joiner.toString() + "}";
    }
}
