package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.util.TypeUtils;

/**
 * Maps serializable objects (e.g null, int, Integer).
 */
class SerializableMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the serializable type {@code that}.
     *
     * @param that a serializable type
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that) {
        if (that != null && TypeUtils.isCompound(that.getClass())) {
            throw new EserialMapperMismatchException(that.getClass());
        }

        return that;
    }
}
