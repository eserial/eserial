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
     * Constructs a {@link DateBuilder} and sets the {@code dateType} in it.
     *
     * @param dateType {@link Date} class to be used in the {@link AbstractBuilder#build} method
     */
    DateBuilder(Type dateType) {
        super(dateType);
    }

    /**
     *
     * @param initializationObject {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a Date object initialized with the given {@link Long} value
     */
    @Override
    public <T> T build(Object initializationObject) {
        if (initializationObject == null) {
            return null;
        }

        Class classOfDateType = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isDate(classOfDateType) || !TypeUtils.isNumber(initializationObject.getClass())) {
            throw new EserialBuilderMismatchException(Date.class.getSimpleName(), classOfDateType.getName());
        }

        return (T) new Date((Long) initializationObject);
    }
}
