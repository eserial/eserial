package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.model.EserialContext;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Builds Dates.
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
     * @param context {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a {@link Date} object initialized with the given {@link Long} value
     */
    @Override
    public <T> T build(Object initializationObject, EserialContext context) {
        if (initializationObject == null) {
            return null;
        }

        Class typeClass = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isDate(typeClass)) {
            throw new EserialBuilderMismatchException(Date.class.getSimpleName(), typeClass.getName());
        }

        if (TypeUtils.isString(initializationObject.getClass())) {
            Long initializationLong;

            try {
                initializationLong = Long.parseLong((String) initializationObject);
            } catch (NumberFormatException e) {
                throw new EserialInputTypeMismatchException("Could not parse String to Long", e);
            }

            return (T) new Date(initializationLong);
        }

        if (!TypeUtils.isLong(initializationObject.getClass())) {
            throw new EserialInputTypeMismatchException(Long.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        return (T) new Date((Long) initializationObject);
    }
}
