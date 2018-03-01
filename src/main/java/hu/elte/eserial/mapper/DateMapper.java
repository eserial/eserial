package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

import java.util.Date;

/**
 * Maps Date objects.
 */
public class DateMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the date {@code that}.
     *
     * @param {@code that} a date
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that, RecursionChecker recursionChecker) {
        if (!TypeUtils.isDate(that.getClass())) {
            throw new EserialMapperMismatchException(Date.class.getSimpleName(), that.getClass().getSimpleName());
        }

        Date date = (Date) that;
        return date.getTime();
    }
}
