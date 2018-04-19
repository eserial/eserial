package hu.elte.eserial.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Contains helper methods for built-in types.
 */
public class TypeUtils {

    /**
     * Prevents the accidental instantiation of this utility class.
     */
    private TypeUtils() {}

    /**
     * Determines if the given {@link Type} is a primitive (excluding void).
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a primitive type
     */
    public static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() && type != void.class;
    }

    /**
     * Determines if the given {@link Type} is a wrapper.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a wrapper type
     */
    public static boolean isWrapper(Class<?> type) {

        return type == Double.class || type == Float.class || type == Long.class
                || type == Integer.class || type == Short.class || type == Character.class
                || type == Byte.class || type == Boolean.class;
    }

    /**
     * Determines if the given {@link Type} is a string.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a string
     */
    public static boolean isString(Class<?> type) {
        return type == String.class;
    }

    /**
     * Determines if the given {@link Type} is compound (not primitive, not wrapper, not a string).
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is compound
     */
    public static boolean isCompound(Class<?> type) {
        return !isPrimitive(type) && !isWrapper(type) && !isString(type);
    }

    /**
     * Determines if the given {@code from} class is assignable from the {@code target} class.
     *
     * @param from is an arbitrary class
     * @param target is an arbitrary class
     * @return {@code true} if {@code target} is assignable from {@code from}
     */
    public static boolean isAssignableFrom(Class from, Class target) {
        return target.isAssignableFrom(from);
    }

    /**
     * Determines if the given {@link Type} is a number.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a number
     */
    public static boolean isNumber(Class<?> type) {
        return Number.class.isAssignableFrom(type) || (isPrimitive(type) && !isBoolean(type) && !isCharacter(type));
    }

    /**
     * Determines if the given {@link Type} is an array.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is an array
     */
    public static boolean isArray(Class<?> type) {
        return type.isArray();
    }

    /**
     * Determines if the given {@link Type} is an enum.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is an enum
     */
    public static boolean isEnum(Class<?> type) {
        return type.isEnum();
    }

    /**
     * Determines if the given {@link Type} is a date.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a date
     */
    public static boolean isDate(Class<?> type) {
        return type == Date.class;
    }
    
    /**
     * Determines if the given {@link Type} is a boolean.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a boolean
     */
    public static boolean isBoolean(Class<?> type) {
        return type == Boolean.class || type == boolean.class;
    }

    /**
     * Determines if the given {@link Type} is a character.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a character
     */
    public static boolean isCharacter(Class<?> type) {
        return type == Character.class || type == char.class;
    }

    /**
     * Determines if the given {@link Type} is a long.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a long
     */
    public static boolean isLong(Class<?> type) {
        return type == Long.class || type == long.class;
    }

    /**
     * Determines if the given {@link Type} is a decimal number.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a long
     */
    public static boolean isDecimal(Class<?> type) {
        return type == double.class || type == Double.class || type == float.class || type == Float.class;
    }

    /**
     * Converts {@code type} to a class.
     *
     * @param type is a {@link Type}
     * @return the class represented by the given {@link Type}
     */
    public static Class convertTypeToClass(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            return (Class) pType.getRawType();
        } else {
            return (Class) type;
        }
    }

    /**
     * Returns the TypeArgument of a {@link Type} if parameterized, else null.
     *
     * @param type is a {@link Type}
     * @param i is the index of the ActualTypeArgument
     * @return  the {@code i}_th type parameter of the given type if parametrized, else {@code null}
     */
    public static Type getTypeArgument(Type type, int i) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            return pType.getActualTypeArguments()[i];
        } else {
            return null;
        }
    }
}
