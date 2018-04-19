package hu.elte.eserial.model;

import java.lang.reflect.Method;

public abstract class Accessor {
    protected Object that;
    protected Method method;

    /**
     * Constructs a new accessor wrapper.
     *
     * @param that an arbitrary object
     * @param method an accessor method: getter or setter
     */
    public Accessor(Object that, Method method) {
        this.that = that;
        this.method = method;
    }

    /**
     * Returns the name of the element from its corresponding accessor method.
     *
     * @return the name of the element
     */
    public abstract String getElementName();

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
