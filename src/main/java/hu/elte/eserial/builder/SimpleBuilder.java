package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
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
     * @return a simple {@link Type} of the given {@link Class} and initialized with the primitive or Wrapper parameter
     */
    @Override
    public <T> T build(Object initializationObject) {
        Class typeClass = TypeUtils.convertTypeToClass(type);

        if (initializationObject == null && (TypeUtils.isString(typeClass)
                || TypeUtils.isWrapper(typeClass))) {
            return null;
        } else if (initializationObject == null) {
            throw new EserialPrimitiveCanNotBeNullException(typeClass.getSimpleName());
        }

        boolean isTypePrimitive = TypeUtils.isPrimitive(typeClass);
        boolean isTypeWrapper = TypeUtils.isWrapper(typeClass);
        boolean isTypeString = TypeUtils.isString(typeClass);

        Class initObjectClass = initializationObject.getClass();
        boolean isInitObjectPrimitive = TypeUtils.isPrimitive(initObjectClass);
        boolean isInitObjectWrapper = TypeUtils.isWrapper(initObjectClass);
        boolean isInitObjectString = TypeUtils.isString(initObjectClass);

        if (!isTypePrimitive && !isTypeWrapper && !isTypeString) {
            throw new EserialBuilderMismatchException(PrimitiveType.class.getSimpleName(), typeClass.getName());
        }

        if (!isInitObjectPrimitive && !isInitObjectWrapper && !isInitObjectString) {
            throw new EserialInputTypeMismatchException(PrimitiveType.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            if (TypeUtils.isNumber(typeClass)) {
                Method numberValueMethod;
                String lowercaseTypeClass = StringUtils.lowercaseFirstLetter(typeClass.getSimpleName());
                if (TypeUtils.isDecimal(typeClass)) {
                    if(TypeUtils.isString(initializationObject.getClass())) {
                        try {
                            initializationObject = Double.parseDouble((String) initializationObject);
                        } catch (NumberFormatException e) {
                            throw new EserialBuilderMismatchException("Could not parse String to Double", e);
                        }
                    }

                    numberValueMethod = Double.class.getDeclaredMethod(lowercaseTypeClass + "Value");
                    return (T) numberValueMethod.invoke(initializationObject);
                } else {
                    if(TypeUtils.isString(initializationObject.getClass())) {
                        try {
                            initializationObject = Long.parseLong((String) initializationObject);
                        } catch (NumberFormatException e) {
                            throw new EserialBuilderMismatchException("Could not parse String to Long", e);
                        }
                    }

                    if (lowercaseTypeClass.equals("integer")) {
                        lowercaseTypeClass = "int";
                    }

                    numberValueMethod = Long.class.getDeclaredMethod(lowercaseTypeClass + "Value");
                    return (T) numberValueMethod.invoke(initializationObject);
                }
            } else if (TypeUtils.isCharacter(typeClass)) {
                if(TypeUtils.isString(initializationObject.getClass())) {
                    String initializationString = (String) initializationObject;

                    if (initializationString.length() > 1) {
                        throw new EserialBuilderMismatchException(Character.class.getSimpleName()
                                , String.class.getSimpleName());
                    }

                    initializationObject = initializationString.charAt(0);
                }

                return (T) initializationObject;
            } else if (TypeUtils.isBoolean(typeClass)) {
                if(TypeUtils.isString(initializationObject.getClass())) {
                    String initializationString = (String) initializationObject;

                    if (!(initializationString.toLowerCase().equals("true"))
                            && !(initializationString.toLowerCase().equals("false"))) {
                        throw new EserialBuilderMismatchException(Boolean.class.getSimpleName()
                                , String.class.getSimpleName());
                    }

                    initializationObject = Boolean.parseBoolean(initializationString);
                }

                return (T) initializationObject;
            } else {
                return (T) initializationObject;
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
           throw new EserialInvalidMethodException("Could not build simple object", e);
        }
    }
}
