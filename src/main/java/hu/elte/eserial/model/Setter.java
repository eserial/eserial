package hu.elte.eserial.model;

import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.MethodUtils;
import hu.elte.eserial.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wrapper class for an instance and a setter method.
 * It can evaluate the setter, furthermore return the name and the type of the corresponding element.
 */
public class Setter {

    private Object that;
    private Method method;

    /**
     * Constructs a new setter wrapper.
     *
     * @param that an arbitrary object
     * @param method a setter method
     * @see MethodUtils#isSetter
     */
    public Setter(Object that, Method method) {
        this.that = that;
        this.method = method;
    }

    /**
     * Returns the name of the element from its corresponding setter method.
     *
     * @return the name of the element
     */
    public String getElementName() {
        Pattern pattern = Pattern.compile("(set)(.+)");
        Matcher matcher = pattern.matcher(method.getName());

        if (matcher.matches()) {
            return StringUtils.lowercaseFirstLetter(matcher.group(2));
        }

        throw new EserialInvalidMethodException(method.getName());
    }

    /**
     * Evaluates the setter method with the given parameter.
     *
     * @param value an arbitrary object
     */
    public void evaluate(Object value) {
        try {
             method.invoke(that, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new EserialInvalidMethodException("Could not evaluate setter", e);
        }
    }

    /**
     * Returns the class of the setter parameter.
     *
     * @return class of {@code method} parameter
     */
    public Class getParameterType() {
        return this.method.getParameterTypes()[0];
    }
}
