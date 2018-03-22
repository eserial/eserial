package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Builds Date-Like objects.
 */
public class DateBuilder extends AbstractBuilder{

    /**
     * Constructs a {@link DateBuilder} and sets the {@code type} in it.
     *
     * @param type {@link Date} class to be used in the {@link AbstractBuilder#build} method
     */
    DateBuilder(Type type) {
        super(type);
    }

    /**
     *
     * @param value {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a Date object initialized with the given {@link Long} value
     */
    @Override
    public <T> T build(Object value) {
        if (value == null) {
            return null;
        }

        Class clazz = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isDate(clazz) || !TypeUtils.isNumber(value.getClass())) {
            throw new EserialBuilderMismatchException(Date.class.getSimpleName(), clazz.getName());
        }

        return (T) new Date((Long) value);
    }
}
