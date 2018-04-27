package hu.elte.eserial.mapper;

import hu.elte.eserial.model.EserialContext;

/**
 * Abstract class for the different object mappers.
 */
public abstract class AbstractMapper {

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
     * @param context an {@link EserialContext} containing information about the context of the element to be mapped
     * @return mapped representation of the contained object
     */
    public abstract Object map(EserialContext context);
}
