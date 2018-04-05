package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.exception.EserialPrimitiveCanNotBeNullException;
import hu.elte.eserial.util.StringUtils;
import hu.elte.eserial.util.TypeUtils;

import javax.lang.model.type.PrimitiveType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Builds simple non-array objects (e.g int, short, Integer, Short).
 */
public class SimpleBuilder extends AbstractBuilder {

    /**
     * Constructs an {@link SimpleBuilder} and sets the {@code type} in it.
     *
     * @param type the simple object to be used in the {@link AbstractBuilder#build} method
     */
    SimpleBuilder(Type type) {
        super(type);
    }

    /**
     * @param value {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a simple object of the given class and initialized with the primitive or Wrapper parameter
     */
    @Override
    public <T> T build(Object value) {
        Class clazz = TypeUtils.convertTypeToClass(type);

        if (value == null && TypeUtils.isString(clazz)) {
            return null;
        } else if (value == null) {
            throw new EserialPrimitiveCanNotBeNullException(clazz.getSimpleName());
        }

        if ((!TypeUtils.isPrimitive(clazz) && !TypeUtils.isWrapper(clazz)) && !TypeUtils.isString(clazz)
                || (!TypeUtils.isPrimitive(value.getClass())
                    && !TypeUtils.isWrapper(value.getClass())
                    && !TypeUtils.isString(value.getClass()))) {
            throw new EserialBuilderMismatchException(PrimitiveType.class.getSimpleName(), clazz.getName());
        }

        try {
            if (TypeUtils.isNumber(clazz)) {
                Method method;
                String lowercaseTypeName = StringUtils.lowercaseFirstLetter(clazz.getSimpleName());
                if (TypeUtils.isDecimal(clazz)) {
                    if(TypeUtils.isString(value.getClass())) {
                        value = Double.parseDouble((String) value);
                    }

                    method = Double.class.getDeclaredMethod(lowercaseTypeName + "Value");
                    return (T) method.invoke(value);
                } else {
                    if(TypeUtils.isString(value.getClass())) {
                        value = Long.parseLong((String) value);
                    }

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
           throw new EserialInvalidMethodException("Could not build simple object", e);
        }
    }
}
