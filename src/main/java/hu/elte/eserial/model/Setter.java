package hu.elte.eserial.model;

import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.MethodUtils;
import hu.elte.eserial.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wrapper class for an instance and a setter method.
 * It can invoke the setter, furthermore return the name and the type of the corresponding element.
 */
public class Setter extends Accessor {

    /**
     * Constructs a new setter wrapper.
     *
     * @param that an arbitrary object
     * @param method a setter method
     * @see MethodUtils#isSetter
     */
    public Setter(Object that, Method method) {
        super(that, method);
    }

    /**
     * Returns the name of the element from its corresponding setter method.
     *
     * @return the name of the element
     */
    @Override
    public String getElementName() {
        Pattern pattern = Pattern.compile("(set)(.+)");
        Matcher matcher = pattern.matcher(method.getName());

        if (matcher.matches()) {
            return StringUtils.lowercaseFirstLetter(matcher.group(2));
        }

        throw new EserialInvalidMethodException(method.getName());
    }

    /**
     * Invokes the setter method with the given parameter.
     *
     * @param value an arbitrary object
     */
    public void invoke(Object value) {
        try {
             method.invoke(that, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new EserialInvalidMethodException("Could not invoke setter", e);
        }
    }

    /**
     * Returns the {@link Type} of the setter parameter.
     *
     * @return {@link Type} of {@code method} parameter
     */
    public Type getTypeOfSetterParameter() {
        return this.method.getGenericParameterTypes()[0];
    }
}
