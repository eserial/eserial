package hu.elte.eserial.builder;

import java.lang.reflect.Type;

/**
 * Abstract class for the different object builders.
 */
abstract class AbstractBuilder {

    protected Type type;

    /**
     * Constructs an {@link AbstractBuilder} descendant and sets the {@code type} in it.
     *
     * @param type the class to be used in the {@link AbstractBuilder#build} method
     */
    AbstractBuilder(Type type) {
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
