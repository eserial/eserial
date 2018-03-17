package hu.elte.eserial.builder;

/**
 * Abstract class for the different object builders.
 */
abstract class AbstractBuilder {

    protected Class type;

    /**
     * Constructs an {@link AbstractBuilder} descendant and sets the {@code type} in it.
     *
     * @param type the class to be used in the {@link AbstractBuilder#build} method
     */
    AbstractBuilder(Class type) {
        this.type = type;
    }

    /**
     * Builds an object with the given type and initializes it with the given value.
     *
     * @param value is the initial value of the object
     * @param <T> is the type of the reference which the returned object is given to
     * @return an object of the given class and initialized with the value parameter
     */
    abstract <T> T build(Object value);
}
