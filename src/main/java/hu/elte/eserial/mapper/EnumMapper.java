package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * Maps Enum-like objects.
 */
public class EnumMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the enum {@code that}.
     *
     * @param {@code that} an enum
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that) {
        if (!TypeUtils.isEnum(that.getClass())) {
            throw new EserialMapperMismatchException(Enum.class.getSimpleName(), that.getClass().getSimpleName());
        }

        Enum enumValue = (Enum) that;
        return enumValue.ordinal();
    }
}
