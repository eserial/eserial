package hu.elte.eserial;

import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.MethodUtils;
import hu.elte.eserial.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wrapper class for an instance and a getter method.
 * It can evaluate the getter and return the name of the corresponding element.
 */
public class Getter {

    private Object that;
    private Method method;

    /**
     * Constructs a new getter wrapper.
     *
     * @param that an arbitrary object
     * @param method a getter method
     * @see MethodUtils#isGetter
     */
    public Getter(Object that, Method method) {
        this.that = that;
        this.method = method;
    }

    /**
     * Returns the name of the element from its corresponding getter method.
     *
     * @return the name of the element
     */
    public String getElementName() {
        Pattern pattern = Pattern.compile("(get|is)(.+)");
        Matcher matcher = pattern.matcher(method.getName());

        if (matcher.matches()) {
            return StringUtils.lowercaseFirstLetter(matcher.group(2));
        }

        throw new EserialInvalidMethodException(method.getName());
    }

    /**
     * Evaluates the getter method and returns its value.
     *
     * @return the value of the getter
     */
    public Object evaluate() {
        try {
            return method.invoke(that);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new EserialInvalidMethodException("Could not evaluate getter", e);
        }
    }
}
