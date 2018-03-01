package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

import java.util.Date;

/**
 * Maps Enum-like objects.
 */
public class EnumMapper extends AbstractMapper {

    /**
     * Constructs an {@link EnumMapper} and sets the {@code object} in it.
     *
     * @param object the {@link Enum} to be used in the {@link AbstractMapper#map} method
     */
    EnumMapper(Object object) {
        super(object);
    }

    /**
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of the contained {@link Enum}
     */
    @Override
    public Object map(RecursionChecker recursionChecker) {
        if (!TypeUtils.isEnum(this.object.getClass())) {
            throw new EserialMapperMismatchException(Enum.class.getSimpleName(),
                    this.object.getClass().getSimpleName());
        }

        Enum enumValue = (Enum) this.object;
        return enumValue.ordinal();
    }
}
