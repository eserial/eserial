package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

/**
 * Maps simple non-array objects (e.g null, int, Integer).
 */
class SimpleMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the simple type {@code that}.
     *
     * @param {@code that} a simple type
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that, RecursionChecker recursionChecker) {
        if (that != null && TypeUtils.isCompound(that.getClass())) {
            throw new EserialMapperMismatchException("Simple", that.getClass().getSimpleName());
        }

        return that;
    }
}
