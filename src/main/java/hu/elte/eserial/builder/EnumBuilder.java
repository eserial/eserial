package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Builds Enum-Like objects.
 */
public class EnumBuilder extends AbstractBuilder {

    /**
     * Constructs an {@link EnumBuilder} and sets the {@code type} in it.
     *
     * @param type {@link Enum} class to be used in the {@link AbstractBuilder#build} method
     */
    EnumBuilder(Type type) {
        super(type);
    }

    /**
     *
     * @param value {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return an Enum object initialized with the given ordinal value
     */
    @Override
    public <T> T build(Object value) {
        if (value == null) {
            return null;
        }

        Class clazz;

        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            clazz = (Class) pType.getRawType();
            throw new EserialBuilderMismatchException (Enum.class.getSimpleName(), clazz.getName());
        } else {
            clazz = (Class) type;
        }

        if (!TypeUtils.isEnum(clazz) || !TypeUtils.isLong(value.getClass())) {
            throw new EserialBuilderMismatchException(Enum.class.getSimpleName(), clazz.getName());
        }

        return (T) clazz.getEnumConstants()[((Long) value).intValue()];
    }
}
