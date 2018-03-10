package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

import java.util.Date;

/**
 * Maps Date objects.
 */
public class DateMapper extends AbstractMapper {

    /**
     * Constructs a {@link DateMapper} and sets the {@code object} in it.
     *
     * @param object the {@link Date} to be used in the {@link AbstractMapper#map} method
     */
    DateMapper(Object object) {
        super(object);
    }

    /**
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of the contained {@link Date}
     */
    @Override
    public Object map(RecursionChecker recursionChecker) {
        if (!TypeUtils.isDate(this.object.getClass())) {
            throw new EserialMapperMismatchException(Date.class.getSimpleName(),
                    this.object.getClass().getSimpleName());
        }

        Date date = (Date) this.object;
        return date.getTime();
    }
}
