package hu.elte.eserial.util;

import java.lang.reflect.Method;

/**
 * Contains helper methods for the built-in Method class.
 */
public final class MethodUtils {

    /**
     * Prevents the accidental instantiation of this utility class.
     */
    private MethodUtils() {}

    /**
     * Determines if the method is a getter.
     *
     * A method is a getter if and only if:
     *  - it does not have any parameters
     *  - its name starts with "get" and is followed by a capital letter and returns non-void or
     *    its name starts with "is" and is followed by a capital letter and returns a boolean
     *
     * @param method an arbitrary method
     * @return {@code true} if {@code method} is a getter
     */
    public static boolean isGetter(Method method) {

        if (method.getParameterCount() > 0) {
            return false;
        }

        String name = method.getName();

        return (name.matches("get[A-Z].*") && method.getReturnType() != void.class)
                || (name.matches("is[A-Z].*") && method.getReturnType() == Boolean.class);
    }

    /**
     * For practical reasons (e.g preventing stack overflow while serializing recursively) we have to
     * ignore some of the built-in getters.
     *
     * @param method an arbitrary method
     * @return {@code true} if the given method is ignored
     */
    public static Boolean isIgnored(Method method) {
        return method.getName().equals("getClass");
    }
}
