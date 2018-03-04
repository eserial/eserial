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
     * <ul>
     *     <li>it does not have any parameters</li>
     *     <li>its name starts with "get" and is followed by a capital letter and returns non-void or
     *         its name starts with "is" and is followed by a capital letter and returns a boolean</li>
     * </ul>
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
     * Determines if the method is a setter.
     *
     * A method is a setter if and only if:
     * <ul>
     *     <li>it does have one parameter only</li>
     *     <li>its name starts with "set"</li>
     *     <li>its return type is void</li>
     * </ul>
     * @param method an arbitrary method
     * @return {@code true} if {@code method} is a setter
     */
    public static boolean isSetter(Method method) {
        if (method.getParameterCount() != 1) {
            return false;
        }

        String name = method.getName();

        return (name.matches("set[A-Z].*") && method.getReturnType() == void.class);
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

    /**
     * @param clazz the class to find the method in
     * @param methodName the name of the method
     * @return a method from the class {@code clazz} with the name {@code methodName} or
     * {@code null} if no such method exists
     */
    public static Method getMethod(Class clazz, String methodName) {
        try {
            return clazz.getDeclaredMethod(methodName);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }
}
