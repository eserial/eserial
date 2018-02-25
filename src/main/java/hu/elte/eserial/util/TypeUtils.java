package hu.elte.eserial.util;

import java.util.Date;

/**
 * Utility class for types.
 */
public class TypeUtils {

    /**
     * {@link Class#isPrimitive()} extended to the primitives' wrapper types, {@link Date} and {@link Iterable}.
     * @param clazz the class to check
     * @return {@code true} if the {@code clazz} can be serialized inline.
     */
    public static boolean isSimpleType(Class clazz) {

        return clazz.isPrimitive()
                || clazz == Boolean.class
                || clazz == Character.class
                || clazz == Byte.class
                || clazz == Short.class
                || clazz == Integer.class
                || clazz == Long.class
                || clazz == Float.class
                || clazz == Double.class
                || clazz == String.class
                || clazz == Date.class
                || clazz == Iterable.class;
    }
}
