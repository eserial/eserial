package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.TypeUtils;

import java.util.Date;

public class DateBuilder extends AbstractBuilder{

    DateBuilder(Class type) {
        super(type);
    }

    @Override
    public <T> T build(Object value) {
        if (!TypeUtils.isDate(type) || !TypeUtils.isNumber(value.getClass())) {
            throw new EserialBuilderMismatchException(Date.class.getSimpleName(), type.getName());
        }

        return (T) new Date((Long) value);
    }
}
