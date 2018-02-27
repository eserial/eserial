package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Array;

/**
 * Maps simple non-array objects (e.g null, int, Integer).
 */
class SimpleMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the simple type {@code that}.
     *
     * @param {@code that} a simple type
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that) {
        if (that != null && TypeUtils.isCompound(that.getClass())) {
            throw new EserialMapperMismatchException("Simple", that.getClass().getSimpleName());
        }

        return that;
    }
}
