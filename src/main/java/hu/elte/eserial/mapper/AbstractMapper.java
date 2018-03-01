package hu.elte.eserial.mapper;

import hu.elte.eserial.recursion.RecursionChecker;

/**
 * Abstract class for the different object mappers.
 */
abstract class AbstractMapper {

    protected Object object;

    /**
     * Constructs an {@link AbstractMapper} descendant and sets the {@code object} in it.
     * @param object the object to be used in the {@link AbstractMapper#map} method
     */
    AbstractMapper(Object object) {
        this.object = object;
    }

    /**
     * Creates a mapped representation of the contained object depending on its type.
     *
     * @param recursionChecker a recursion checker instance or {@code null} if this element is considered
     *                         the root of a compound object
     * @return mapped representation of the contained object
     */
    abstract Object map(RecursionChecker recursionChecker);
}
