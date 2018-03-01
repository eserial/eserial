package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

/**
 * Maps Enum-like objects.
 */
public class EnumMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the enum {@code that}.
     *
     * @param {@code that} an enum
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that, RecursionChecker recursionChecker) {
        if (!TypeUtils.isEnum(that.getClass())) {
            throw new EserialMapperMismatchException(Enum.class.getSimpleName(), that.getClass().getSimpleName());
        }

        Enum enumValue = (Enum) that;
        return enumValue.ordinal();
    }
}
