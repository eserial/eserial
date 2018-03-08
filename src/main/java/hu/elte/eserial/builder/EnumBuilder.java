package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.TypeUtils;

public class EnumBuilder {

    public static Object build(Object value, Class<?> type) throws Exception {
        if (!TypeUtils.isEnum(type) || !Long.class.isInstance(value)) {
            throw new EserialException("Type mismatch");
        }

        return type.getEnumConstants()[((Long) value).intValue()];
    }
}
