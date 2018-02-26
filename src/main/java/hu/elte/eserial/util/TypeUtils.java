package hu.elte.eserial.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;

import hu.elte.eserial.util.model.TypeDepth;

/**
 * Utility class for types.
 */
public class TypeUtils {

    /**
     * {@link Class#isPrimitive()} extended to the primitives' wrapper types, {@link Date} and {@link Collection}.
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
                || clazz == Collection.class;
    }

    /**
     * Constructs a {@link TypeDepth} with the depth information of the given {@code type}.<br>
     * <br>
     * Examples:<br>
     * <ul>
     *   <li>getTypeDepth({@code String}) == (String, 0)</li>
     *   <li>getTypeDepth({@code List<String>}) == (String, 1)</li>
     *   <li>getTypeDepth({@code List<List<String>>}) == (String, 2)</li>
     * </ul>
     *
     * @param type a type (coming from a Field or Method)
     * @return a TypeDepth instance with the innermost type and the generic depth of the type
     *
     * @exception IllegalArgumentException if the given {@code type} has more than 1 generic type parameter
     */
    public static TypeDepth getTypeDepth(Type type) {
        int depth = 0;
        while (type instanceof ParameterizedType) {
            if (((ParameterizedType) type).getActualTypeArguments().length > 1) {
                throw new IllegalArgumentException("Maps are currently not supported.");
            }
            type = ((ParameterizedType) type).getActualTypeArguments()[0];
            depth++;
        }
        return new TypeDepth(type, depth);
    }
}
