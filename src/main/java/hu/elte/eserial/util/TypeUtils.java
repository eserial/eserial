package hu.elte.eserial.util;

import java.util.*;
import java.util.concurrent.*;

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
     * Determines if the given type is a number.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a number
     */
    public static Boolean isNumber(Class<?> type) {
        return Number.class.isAssignableFrom(type) || (isPrimitive(type) && !isBoolean(type) && !isCharacter(type));
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
     * Determines if the given type is a date.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a date
     */
    public static Boolean isDate(Class<?> type) {
        return type == Date.class;
    }

    /**
     * Determines if the given type is aSortedSet.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a sortedset
     */
    public static Boolean isSortedSet(Class<?> type) {
        return SortedSet.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a Set.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a set
     */
    public static Boolean isSet(Class<?> type) {
        return Set.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a Queue.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a queue
     */
    public static Boolean isQueue(Class<?> type) {
        return Queue.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a ConcurrentNavigableMap.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a concurrentnavigablemap
     */
    public static Boolean isConcurrentNavigableMap(Class<?> type) {
        return ConcurrentNavigableMap.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a ConcurrentMap.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a concurrentmap
     */
    public static Boolean isConcurrentMap(Class<?> type) {
        return ConcurrentMap.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a SortedMap.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a sortedmap
     */
    public static Boolean isSortedMap(Class<?> type) {
        return SortedMap.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a BlockingQueue.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a blockingqueue
     */
    public static Boolean isBlockingQueue(Class<?> type) {
        return BlockingQueue.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a BlockingDeque.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a blockingdeque
     */
    public static Boolean isBlockingDeque(Class<?> type) {
        return BlockingDeque.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a TransferQueue.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a transferqueue
     */
    public static Boolean isTransferQueue(Class<?> type) {
        return TransferQueue.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a list.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a list
     */
    public static Boolean isList(Class<?> type) {
        return List.class.isAssignableFrom(type);
    }

    /**
     * Determines if the given type is a boolean.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a boolean
     */
    public static Boolean isBoolean(Class<?> type) {
        return type == Boolean.class || type == boolean.class;
    }

    /**
     * Determines if the given type is a character.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a character
     */
    public static Boolean isCharacter(Class<?> type) {
        return type == Character.class || type == char.class;
    }

    /**
     * Determines if the gives type is a long.
     *
     * @param type an arbitrary class
     * @return {@code true} if {@code type} is a long
     */
    public static Boolean isLong(Class<?> type) {
        return Long.class.isAssignableFrom(type);
    }
}
