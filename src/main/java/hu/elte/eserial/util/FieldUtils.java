package hu.elte.eserial.util;

import java.lang.reflect.Field;

/**
 * Utility class for fields.
 */
public class FieldUtils {

    private FieldUtils() {}

    /**
     * @param clazz the class to find the field in
     * @param fieldName the name of the field
     * @return a field from the class {@code clazz} with the name {@code fieldName} or
     * {@code null} if no such field exists
     */
    public static Field getField(Class clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException e) {
            return null;
        }
    }
}
