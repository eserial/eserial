package hu.elte.eserial.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for fields.
 */
public class FieldUtils {

    /**
     * Extracts a (virtual) field name from a getter method.
     * @param getter the getter method of the (virtual) field
     * @return the method's name without the first "get" part and
     * the remaining string's first letter in lowercase or {@code null} if
     * the method's name does not match a getter's format.
     */
    public static String getFieldName(Method getter) {
        Pattern pattern = Pattern.compile("get([A-Z].*)");
        Matcher matcher = pattern.matcher(getter.getName());

        String fieldName;
        if (matcher.matches()) {
            fieldName = matcher.group(1);
            return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
        }
        else {
            return null;
        }
    }

    /**
     * @param clazz the class to find the field in
     * @param fieldName the name of the field
     * @return a field from the class {@code clazz} with the name {@fieldName} or
     * {@code null} if no such field exists
     */
    public static Field getField(Class clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }
}
