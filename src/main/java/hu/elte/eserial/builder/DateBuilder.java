package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.TypeUtils;

import java.util.Date;

public class DateBuilder {

    public static Object build(Object value, Class type) {
        if (!TypeUtils.isDate(type) || !TypeUtils.isNumber(value.getClass())) {
            throw new EserialException("Type mismatch");
        }

        return new Date((Long) value);
    }
}
