package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.StringUtils;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Method;

public class PrimitiveBuilder {

    public static Object build(Object value, Class<?> type) throws Exception {
        if ((!TypeUtils.isPrimitive(type) && !TypeUtils.isWrapper(type)) || (!TypeUtils.isPrimitive(value.getClass()) && !TypeUtils.isWrapper(value.getClass()))) {
            throw new EserialException("Type mismatch");
        }

        if (Number.class.isInstance(value)) {
            Method method;
            String lowercaseTypeName = StringUtils.lowercaseFirstLetter(type.getSimpleName());
            if (Double.class.isInstance(value)) {
                method = Double.class.getDeclaredMethod(lowercaseTypeName + "Value");
                return method.invoke(value);
            } else {
                if (lowercaseTypeName.equals("integer")) {
                    lowercaseTypeName = "int";
                }

                method = Long.class.getDeclaredMethod(lowercaseTypeName + "Value");
                return method.invoke(value);
            }
        } else {
            return value;
        }
    }

}
