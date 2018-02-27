package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.util.TypeUtils;

/**
 * Maps Enum-like objects.
 */
public class EnumMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the enum {@code that}.
     *
     * @param that an enum
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that) {
        if (!TypeUtils.isEnum(that.getClass())) {
            throw new EserialMapperMismatchException(that.getClass());
        }

        Enum enumValue = (Enum) that;
        return enumValue.ordinal();
    }
}
