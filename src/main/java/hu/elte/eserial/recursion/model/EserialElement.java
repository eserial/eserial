package hu.elte.eserial.recursion.model;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * An <b>element</b> is defined by its accessor method (getter or setter) and the corresponding value.<br>
 * This is a convenience class wrapping these two with {@code equals} and {@code hashCode} methods.
 */
public class EserialElement {

    private Method accessor;
    private Object value;

    public EserialElement(Method accessor, Object value) {
        this.accessor = accessor;
        this.value = value;
    }

    public Method getAccessor() {
        return accessor;
    }

    public void setAccessor(Method accessor) {
        this.accessor = accessor;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @param obj another object
     * @return true if all of these is true:
     * <ul>
     *     <li>accessor names are equal</li>
     *     <li>accessor return types are equal</li>
     *     <li>accessor declaring classes are equal</li>
     *     <li>the <b>references</b> of the values are equal</li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EserialElement that = (EserialElement) obj;
        return this.accessor.getName().equals(that.accessor.getName())
                && this.accessor.getReturnType() == that.accessor.getReturnType()
                && this.accessor.getDeclaringClass() == that.accessor.getDeclaringClass()
                && this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessor, value);
    }
}
