package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.exception.EserialPrimitiveCanNotBeNullException;
import hu.elte.eserial.util.StringUtils;
import hu.elte.eserial.util.TypeUtils;

import javax.lang.model.type.PrimitiveType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Builds simple non-array objects (e.g int, short, Integer, Short).
 */
public class SimpleBuilder extends AbstractBuilder {

    /**
     * Constructs an {@link SimpleBuilder} and sets the {@code type} in it.
     *
     * @param type the simple object to be used in the {@link AbstractBuilder#build} method
     */
    SimpleBuilder(Class type) {
        super(type);
    }

    /**
     * @param value {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a simple object of the given class and initialized with the primitive or Wrapper parameter
     */
    @Override
    public <T> T build(Object value) {
        if (value == null && TypeUtils.isString(type)) {
            return null;
        } else if (value == null) {
            throw new EserialPrimitiveCanNotBeNullException(type.getSimpleName());
        }

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
