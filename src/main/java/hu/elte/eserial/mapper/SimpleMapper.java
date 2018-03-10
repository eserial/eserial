package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

/**
 * Maps simple non-array objects (e.g null, int, Integer).
 */
class SimpleMapper extends AbstractMapper {

    /**
     * Constructs an {@link EnumMapper} and sets the {@code object} in it.
     *
     * @param object the simple object to be used in the {@link AbstractMapper#map} method
     */
    SimpleMapper(Object object) {
        super(object);
    }

    /**
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of the contained simple object
     */
    @Override
    public Object map(RecursionChecker recursionChecker) {
        if (this.object != null && TypeUtils.isCompound(this.object.getClass())) {
            throw new EserialMapperMismatchException("Simple", this.object.getClass().getSimpleName());
        }

        return this.object;
    }
}
