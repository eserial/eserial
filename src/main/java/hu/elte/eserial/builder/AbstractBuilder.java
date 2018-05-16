package hu.elte.eserial.builder;

import hu.elte.eserial.model.EserialContext;

import java.lang.reflect.Type;

/**
 * Abstract class for the different object builders.
 */
public abstract class AbstractBuilder {

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
     * @param context an {@link EserialContext} containing information about the context of the element to be built
     * @param <T> is the {@link Type} of the reference which the returned {@link Object} is given to
     * @return an {@link Object} of the given {@link Type} and initialized with the {@code initializationObject} parameter
     */
    public abstract <T> T build(Object initializationObject, EserialContext context);
}
