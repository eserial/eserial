package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
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

        Class classOfEnumType = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isEnum(classOfEnumType)) {
            throw new EserialBuilderMismatchException(Enum.class.getSimpleName(), classOfEnumType.getName());
        }

        if (TypeUtils.isString(initializationObject.getClass())) {
            Long initializationLong;

            try {
                initializationLong = Long.parseLong((String) initializationObject);
            } catch (NumberFormatException e) {
                throw new EserialBuilderMismatchException("Could not parse String to Long", e);
            }

            return (T) classOfEnumType.getEnumConstants()[initializationLong.intValue()];
        }

        if (!TypeUtils.isLong(initializationObject.getClass())) {
            throw new EserialBuilderMismatchException(Long.class.getSimpleName(), initializationObject.getClass().getName());
        }

        return (T) classOfEnumType.getEnumConstants()[((Long) initializationObject).intValue()];
    }
}
