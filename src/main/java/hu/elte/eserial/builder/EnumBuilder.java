package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.exception.EserialInvalidEnumOrdinalException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;

/**
 * Builds Enums.
 */
public class EnumBuilder extends AbstractBuilder {

    /**
     * Constructs an {@link EnumBuilder} and sets the {@code enumType} in it.
     *
     * @param enumType {@link Enum} class to be used in the {@link AbstractBuilder#build} method
     */
    EnumBuilder(Type enumType) {
        super(enumType);
    }

    /**
     *
     * @param initializationObject {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return an {@link Enum} object initialized with the given ordinal value
     */
    @Override
    public <T> T build(Object initializationObject) {
        if (initializationObject == null) {
            return null;
        }

        Class typeClass = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isEnum(typeClass)) {
            throw new EserialBuilderMismatchException(Enum.class.getSimpleName(), typeClass.getName());
        }

        if (!TypeUtils.isLong(initializationObject.getClass()) && !TypeUtils.isString(initializationObject.getClass())) {
            throw new EserialInputTypeMismatchException(Long.class.getSimpleName(), initializationObject.getClass().getName());
        }

        Long initializationLong;

        if (TypeUtils.isString(initializationObject.getClass())) {
            try {
                initializationLong = Long.parseLong((String) initializationObject);
            } catch (NumberFormatException e) {
                throw new EserialInputTypeMismatchException("Could not parse String to Long", e);
            }
        } else {
            initializationLong = (Long) initializationObject;
        }

        if (initializationLong < 0 || typeClass.getEnumConstants().length - 1 < initializationLong) {
            throw new EserialInvalidEnumOrdinalException();
        }

        return (T) typeClass.getEnumConstants()[initializationLong.intValue()];
    }
}
