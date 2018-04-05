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
     * @param initializationObject {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a simple object of the given class and initialized with the primitive or Wrapper parameter
     */
    @Override
    public <T> T build(Object initializationObject) {
        Class clazz = TypeUtils.convertTypeToClass(type);

        if (initializationObject == null && TypeUtils.isString(clazz)) {
            return null;
        } else if (initializationObject == null) {
            throw new EserialPrimitiveCanNotBeNullException(clazz.getSimpleName());
        }

        if ((!TypeUtils.isPrimitive(clazz) && !TypeUtils.isWrapper(clazz)) && !TypeUtils.isString(clazz)
                || (!TypeUtils.isPrimitive(initializationObject.getClass())
                    && !TypeUtils.isWrapper(initializationObject.getClass())
                    && !TypeUtils.isString(initializationObject.getClass()))) {
            throw new EserialBuilderMismatchException(PrimitiveType.class.getSimpleName(), clazz.getName());
        }

        try {
            if (TypeUtils.isNumber(clazz)) {
                Method method;
                String lowercaseTypeName = StringUtils.lowercaseFirstLetter(clazz.getSimpleName());
                if (TypeUtils.isDecimal(clazz)) {
                    if(TypeUtils.isString(initializationObject.getClass())) {
                        initializationObject = Double.parseDouble((String) initializationObject);
                    }

                    method = Double.class.getDeclaredMethod(lowercaseTypeName + "Value");
                    return (T) method.invoke(initializationObject);
                } else {
                    if(TypeUtils.isString(initializationObject.getClass())) {
                        initializationObject = Long.parseLong((String) initializationObject);
                    }

                    if (lowercaseTypeName.equals("integer")) {
                        lowercaseTypeName = "int";
                    }

                    method = Long.class.getDeclaredMethod(lowercaseTypeName + "Value");
                    return (T) method.invoke(initializationObject);
                }
            } else {
                return (T) initializationObject;
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
           throw new EserialInvalidMethodException("Could not build simple object", e);
        }
    }
}
