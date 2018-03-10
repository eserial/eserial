package hu.elte.eserial.model;

import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.FieldUtils;
import hu.elte.eserial.util.MethodUtils;

import java.lang.reflect.Field;

/**
 * Stores information about an element: its corresponding field, getter, containing class. Also contains a recursion
 * checker instance to use during the serialization process.
 */
public class EserialContext {

    private Field field;
    private Getter getter;
    private Class containingClass;
    private RecursionChecker recursionChecker;

    /**
     * An {@link EserialContext} should only be created using the static factory methods to ensure consistency.
     */
    private EserialContext() {}

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Getter getGetter() {
        return getter;
    }

    public void setGetter(Getter getter) {
        this.getter = getter;
    }

    public Class getContainingClass() {
        return containingClass;
    }

    public void setContainingClass(Class containingClass) {
        this.containingClass = containingClass;
    }

    public RecursionChecker getRecursionChecker() {
        return recursionChecker;
    }

    public void setRecursionChecker(RecursionChecker recursionChecker) {
        this.recursionChecker = recursionChecker;
    }

    /**
     * Constructs an {@link EserialContext} from a containing object, a getter's name and a recursion checker.
     * @param object the containing object of the element which the context is built for
     * @param getterName the name of the getter in the {@code object}'s class
     * @param recursionChecker a {@link RecursionChecker} instance
     * @return An {@link EserialContext} with its field, getter, containing class and recursion checker set.
     *
     * @throws NullPointerException if object is null
     */
    public static EserialContext forElement(Object object, String getterName, RecursionChecker recursionChecker) {
        Getter getter = new Getter(object, MethodUtils.getMethod(object.getClass(), getterName));

        EserialContext context = new EserialContext();
        context.setGetter(getter);
        context.setField(FieldUtils.getField(object.getClass(), getter.getElementName()));
        context.setContainingClass(object.getClass());
        context.setRecursionChecker(recursionChecker);
        return context;
    }

    /**
     * Constructs an {@link EserialContext} from a root object and creates a {@link RecursionChecker} for it, too.
     * @param object a root object
     * @return An {@link EserialContext} with its containing class and recursion checker set.
     *
     * @throws NullPointerException if object is null
     */
    public static EserialContext forRoot(Object object) {
        EserialContext context = new EserialContext();
        context.setContainingClass(object.getClass());
        context.setRecursionChecker(new RecursionChecker(object));
        return context;
    }
}
