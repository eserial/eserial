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
     * Constructs a {@link SimpleBuilder} and sets the {@code simpleType} in it.
     *
     * @param simpleType the simple object to be used in the {@link AbstractBuilder#build} method
     */
    SimpleBuilder(Type simpleType) {
        super(simpleType);
    }

    /**
     * @param initializationObject {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a simple type of the given class and initialized with the primitive or Wrapper parameter
     */
    @Override
    public <T> T build(Object initializationObject) {
        Class classOfSimpleType = TypeUtils.convertTypeToClass(type);

        if (initializationObject == null && TypeUtils.isString(classOfSimpleType)
                && TypeUtils.isWrapper(classOfSimpleType)) {
            return null;
        } else if (initializationObject == null) {
            throw new EserialPrimitiveCanNotBeNullException(classOfSimpleType.getSimpleName());
        }

        boolean isTypePrimitive = TypeUtils.isPrimitive(classOfSimpleType);
        boolean isTypeWrapper = TypeUtils.isWrapper(classOfSimpleType);
        boolean isTypeString = TypeUtils.isString(classOfSimpleType);

        Class initializationObjectClass = initializationObject.getClass();
        boolean isInitObjectPrimitive = TypeUtils.isPrimitive(initializationObjectClass);
        boolean isInitObjectWrapper = TypeUtils.isWrapper(initializationObjectClass);
        boolean isInitObjectString = TypeUtils.isString(initializationObjectClass);

        if (!isTypePrimitive && !isTypeWrapper && !isTypeString) {
            throw new EserialBuilderMismatchException(PrimitiveType.class.getSimpleName(), classOfSimpleType.getName());
        }

        if (!isInitObjectPrimitive && !isInitObjectWrapper && !isInitObjectString) {
            throw new EserialBuilderMismatchException(PrimitiveType.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            if (TypeUtils.isNumber(classOfSimpleType)) {
                Method numberToLongOrDoubleMethod;
                String lowercaseClassOfSimpleType = StringUtils.lowercaseFirstLetter(classOfSimpleType.getSimpleName());
                if (TypeUtils.isDecimal(classOfSimpleType)) {
                    if(TypeUtils.isString(initializationObject.getClass())) {
                        try {
                            initializationObject = Double.parseDouble((String) initializationObject);
                        } catch (NumberFormatException e) {
                            throw new EserialBuilderMismatchException("Could not parse String to Double", e);
                        }
                    }

                    numberToLongOrDoubleMethod = Double.class.getDeclaredMethod(lowercaseClassOfSimpleType + "Value");
                    return (T) numberToLongOrDoubleMethod.invoke(initializationObject);
                } else {
                    if(TypeUtils.isString(initializationObject.getClass())) {
                        try {
                            initializationObject = Long.parseLong((String) initializationObject);
                        } catch (NumberFormatException e) {
                            throw new EserialBuilderMismatchException("Could not parse String to Long", e);
                        }
                    }

                    if (lowercaseClassOfSimpleType.equals("integer")) {
                        lowercaseClassOfSimpleType = "int";
                    }

                    numberToLongOrDoubleMethod = Long.class.getDeclaredMethod(lowercaseClassOfSimpleType + "Value");
                    return (T) numberToLongOrDoubleMethod.invoke(initializationObject);
                }
            } else {
                return (T) initializationObject;
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
           throw new EserialInvalidMethodException("Could not build simple object", e);
        }
    }
}
