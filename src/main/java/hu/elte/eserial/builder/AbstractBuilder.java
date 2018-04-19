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
     * @param type the {@link Type} to be used in the {@link AbstractBuilder#build} method
     */
    AbstractBuilder(Type type) {
        this.type = type;
    }

    /**
     * Builds an {@link Object} with the given {@code type} and initializes it from the given {@link Object} parameter.
     *
     * @param initializationObject is the initial value of the {@link Object}
     * @param <T> is the {@link Type} of the reference which the returned {@link Object} is given to
     * @return an {@link Object} of the given {@link Type} and initialized with the {@code initializationObject} parameter
     */
    abstract <T> T build(Object initializationObject);
}
