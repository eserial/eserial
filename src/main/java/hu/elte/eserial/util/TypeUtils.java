package hu.elte.eserial.util;

import java.util.Date;
import java.util.Collection;
import java.util.Map;

/**
 * Contains helper methods for built-in types.
 */
public class TypeUtils {

    /**
     * Prevents the accidental instantiation of this utility class.
     */
    private TypeUtils() {}

    /**
     * Determines if the given type is a primitive (excluding void).
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a primitive type
     */
    public static Boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() && type != void.class;
    }

    /**
     * Determines if the given type is a wrapper.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a wrapper type
     */
    public static Boolean isWrapper(Class<?> type) {

        return type == Double.class || type == Float.class || type == Long.class
                || type == Integer.class || type == Short.class || type == Character.class
                || type == Byte.class || type == Boolean.class;
    }

    /**
     * Determines if the given type is a string.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a string
     */
    public static Boolean isString(Class<?> type) {
        return type == String.class;
    }

    /**
     * Determines if the given type is compound (not primitive, not wrapper, not a string).
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is compound
     */
    public static Boolean isCompound(Class<?> type) {
        return !isPrimitive(type) && !isWrapper(type) && !isString(type);
    }

    /**
     * Determines if the given type is a collection.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a collection
     */
    public static Boolean isCollection(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a map.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a map
     */
    public static Boolean isMap(Class<?> type) {
        return Map.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is an array.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is an array
     */
    public static Boolean isArray(Class<?> type) {
        return type.isArray();
    }

    /**
     * Determines if the given type is an enum.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is an enum
     */
    public static Boolean isEnum(Class<?> type) {
        return type.isEnum();
    }

    /**
     * Determines if the given type is an date.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a date
     */
    public static Boolean isDate(Class<?> type) {
        return type == Date.class;
    }
}
