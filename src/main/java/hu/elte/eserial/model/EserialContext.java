package hu.elte.eserial.model;

import hu.elte.eserial.recursion.RecursionChecker;

/**
 * Stores information about an element: its corresponding field, getter, containing class. Also contains a recursion
 * checker instance to use during the serialization process.
 */
public class EserialContext {

    private EserialElement element;
    private RecursionChecker recursionChecker;

    /**
     * An {@link EserialContext} should only be created using the static factory methods to ensure consistency.
     */
    private EserialContext() {}

    public EserialElement getElement() {
        return element;
    }

    public void setElement(EserialElement element) {
        this.element = element;
    }

    public RecursionChecker getRecursionChecker() {
        return recursionChecker;
    }

    public void setRecursionChecker(RecursionChecker recursionChecker) {
        this.recursionChecker = recursionChecker;
    }

    /**
     * Constructs an {@link EserialContext} for an {@link EserialElement} for mappers
     * @param element an {@link EserialElement}
     * @param recursionChecker a {@link RecursionChecker}
     * @return an {@link EserialContext} with the given properties
     */
    public static EserialContext forMapperElement(EserialElement element, RecursionChecker recursionChecker) {
        EserialContext eserialContext = new EserialContext();
        eserialContext.setElement(element);
        eserialContext.setRecursionChecker(recursionChecker);
        return eserialContext;
    }

    /**
     * Constructs an {@link EserialContext} for an {@link EserialElement} for builders
     * @param element an {@link EserialElement}
     * @return an {@link EserialContext} with the given {@link EserialElement}
     */
    public static EserialContext forBuilderElement(EserialElement element) {
        EserialContext eserialContext = new EserialContext();
        eserialContext.setElement(element);
        return eserialContext;
    }

    /**
     * Constructs an {@link EserialContext} from a root object and creates a {@link RecursionChecker} for it, too.
     * @param object a root object
     * @return an {@link EserialContext} with its containing class and recursion checker set
     *
     * @throws NullPointerException if object is null
     */
    public static EserialContext forRoot(Object object) {
        EserialContext context = new EserialContext();
        context.setElement(EserialElement.fromContainingClass(object.getClass()));
        context.setRecursionChecker(new RecursionChecker(object));
        return context;
    }
}
