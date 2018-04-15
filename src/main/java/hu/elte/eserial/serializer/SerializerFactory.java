package hu.elte.eserial.serializer;

import hu.elte.eserial.util.TypeUtils;

import java.util.List;

/**
 * Provides an adequate serializer implementation for a given type.
 */
public class SerializerFactory {

    /**
     * Prevents the accidental instantiation of this factory class.
     */
    private SerializerFactory() {}

    /**
     * Creates a type-specific object mapper instance.
     *
     * @param object an arbitrary object
     * @return a serializer for {@code object}
     */
    public static AbstractSerializer create(Object object) {
        if (object == null) {
            return new NullSerializer(null);
        } else {
            Class<?> type = object.getClass();

            if (TypeUtils.isNumber(type)) {
                return new NumberSerializer(object);
            } else if (TypeUtils.isBoolean(type)) {
                return new BooleanSerializer(object);
            } else if (TypeUtils.isString(type) || TypeUtils.isCharacter(type)) {
                return new StringSerializer(object);
            } else if (TypeUtils.isAssignableFrom(type, List.class)) {
                return new ArraySerializer(object);
            } else {
                return new ObjectSerializer(object);
            }
        }
    }
}
