package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.TypeUtils;

/**
 * Builds Enum-Like objects.
 */
public class EnumBuilder extends AbstractBuilder {

    /**
     * Constructs an {@link EnumBuilder} and sets the {@code type} in it.
     *
     * @param type {@link Enum} class to be used in the {@link AbstractBuilder#build} method
     */
    EnumBuilder(Class type) {
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

        if (!TypeUtils.isEnum(type) || !TypeUtils.isLong(value.getClass())) {
            throw new EserialBuilderMismatchException(Enum.class.getSimpleName(), type.getName());
        }

        return (T) type.getEnumConstants()[((Long) value).intValue()];
    }
}
