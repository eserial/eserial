package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Map;

/**
 * Maps Date objects.
 */
public class DateMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the date {@code that}.
     *
     * @param {@code that} a date
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that) {
        if (!TypeUtils.isDate(that.getClass())) {
            throw new EserialMapperMismatchException(Date.class.getSimpleName(), that.getClass().getSimpleName());
        }

        Date date = (Date) that;
        return date.getTime();
    }
}
