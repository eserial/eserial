package hu.elte.eserial.model;

import hu.elte.eserial.util.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * An <b>element</b> is defined by its accessor method (getter or setter) and the corresponding value.<br>
 * This is a convenience class wrapping these two with interfaces for accessing the containing class and
 * the corresponding field.
 */
public class EserialElement {

    private Accessor accessor;
    private Object value;
    private Class containingClass;
    private Field field;

    private EserialElement() {}

    /**
     * Constructs an {@link EserialElement} with only a value.
     * @param value the value of the element
     * @return an {@link EserialElement} with the given value
     */
    public static EserialElement fromValue(Object value) {
        EserialElement element = new EserialElement();
        element.setValue(value);
        return element;
    }

    /**
     * Constructs an {@link EserialElement} with only an {@link Accessor}. Sets the containing class and field
     * properties read from the given {@code accessor}.
     * @param accessor the accessor of the element
     * @return an {@link EserialElement} with the above properties
     */
    public static EserialElement fromAccessor(Accessor accessor) {
        EserialElement element = new EserialElement();
        element.setAccessor(accessor);
        element.setContainingClass(accessor.getMethod().getDeclaringClass());
        element.setField(FieldUtils.getField(element.getContainingClass(), accessor.getElementName()));
        return element;
    }

    /**
     * Constructs an {@link EserialElement} with an {@link Accessor} and a value. Sets the containing class and field
     * properties read from the given {@code accessor}. The accessor and value define the equality between
     * {@link EserialElement}s when checking for recursion.
     * @param accessor the accessor of the element
     * @param value the value of the element
     * @return an {@link EserialElement} with the above properties
     */
    public static EserialElement fromAccessorAndValue(Accessor accessor, Object value) {
        EserialElement element = new EserialElement();
        element.setAccessor(accessor);
        element.setValue(value);
        element.setContainingClass(accessor.getMethod().getDeclaringClass());
        element.setField(FieldUtils.getField(element.getContainingClass(), accessor.getElementName()));
        return element;
    }

    /**
     * Constructs an {@link EserialElement} with only a containing class.
     * @param containingClass the containing class of the element
     * @return an {@link EserialElement} with the given containing class
     */
    public static EserialElement fromContainingClass(Class containingClass) {
        EserialElement element = new EserialElement();
        element.setContainingClass(containingClass);
        return element;
    }

    public Accessor getAccessor() {
        return accessor;
    }

    public void setAccessor(Accessor accessor) {
        this.accessor = accessor;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class getContainingClass() {
        return containingClass;
    }

    public void setContainingClass(Class containingClass) {
        this.containingClass = containingClass;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
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
    public boolean equalsForRecursion(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EserialElement that = (EserialElement) obj;

        if (this.accessor == null && that.accessor == null) {
            return this.value == that.value;
        }
        else if (this.accessor == null || that.accessor == null) {
            return false;
        }
        else {
            Method thisMethod = this.accessor.getMethod();
            Method thatMethod = that.accessor.getMethod();
            return thisMethod.getName().equals(thatMethod.getName())
                && thisMethod.getReturnType() == thatMethod.getReturnType()
                && thisMethod.getDeclaringClass() == thatMethod.getDeclaringClass()
                && this.value == that.value;
        }
    }
}
