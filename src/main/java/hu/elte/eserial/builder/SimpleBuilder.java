package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.StringUtils;
import hu.elte.eserial.util.TypeUtils;

import javax.lang.model.type.PrimitiveType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;

public class SimpleBuilder extends AbstractBuilder {

    SimpleBuilder(Class type) {
        super(type);
    }

    @Override
    public <T> T build(Object value) {
        if ((!TypeUtils.isPrimitive(type) && !TypeUtils.isWrapper(type)) && !TypeUtils.isString(type)
                || (!TypeUtils.isPrimitive(value.getClass())
                    && !TypeUtils.isWrapper(value.getClass())
                    && !TypeUtils.isString(value.getClass()))) {
            throw new EserialBuilderMismatchException(String.format("%s, %s, %s"
                    , "Primitive", "Wrapper", String.class.getSimpleName()), type.getName());
        }

        try {
            if (TypeUtils.isNumber(type)) {
                Method method;
                String lowercaseTypeName = StringUtils.lowercaseFirstLetter(type.getSimpleName());
                if (Double.class.isInstance(value)) {
                    method = Double.class.getDeclaredMethod(lowercaseTypeName + "Value");
                    return (T) method.invoke(value);
                } else {
                    if (lowercaseTypeName.equals("integer")) {
                        lowercaseTypeName = "int";
                    }

                    method = Long.class.getDeclaredMethod(lowercaseTypeName + "Value");
                    return (T) method.invoke(value);
                }
            } else {
                return (T) value;
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new EserialInvalidMethodException(e.getMessage());
        }
    }
}
