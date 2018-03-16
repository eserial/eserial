package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.TypeUtils;

public class EnumBuilder extends AbstractBuilder {

    EnumBuilder(Class type) {
        super(type);
    }

    @Override
    public <T> T build(Object value) {
        if (!TypeUtils.isEnum(type) || !Long.class.isInstance(value)) {
            throw new EserialBuilderMismatchException(Enum.class.getSimpleName(), type.getName());
        }

        return (T) type.getEnumConstants()[((Long) value).intValue()];
    }
}
