package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.exception.EserialNoDefaultConstructorException;
import hu.elte.eserial.model.Setter;
import hu.elte.eserial.util.MethodUtils;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Builds Compound objects.
 */
public class CompoundBuilder extends AbstractBuilder {

    /**
     * Constructs a {@link CompoundBuilder} and sets the {@code objectType} in it.
     *
     * @param objectType the class to be used in the {@link AbstractBuilder#build} method
     */
    CompoundBuilder(Type objectType) {
        super(objectType);
    }

    /**
     * @param initializationObject {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a compound object of the given {@link Type} and initialized with the {@link Map} parameter
     */
    @Override
    public <T> T build(Object initializationObject) {
        if (initializationObject == null) {
            return null;
        }

        Class typeClass = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isCompound(typeClass)) {
            throw new EserialBuilderMismatchException("Compound type", typeClass.getSimpleName());
        }

        if (!TypeUtils.isAssignableFrom(initializationObject.getClass(), Map.class)) {
            throw new EserialInputTypeMismatchException(Map.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            Object objectInstance = typeClass.newInstance();

            for (Method method : typeClass.getMethods()) {
                if (MethodUtils.isIgnored(method) || !MethodUtils.isSetter(method)) {
                    continue;
                }

                Map<String, Object> initializationMap = (Map) initializationObject;

                Setter setter = new Setter(objectInstance, method);

                String dataMemberName = setter.getElementName();
                Object inputValue = initializationMap.get(dataMemberName);

                Type dataMemberType = setter.getTypeOfSetterParameter();

                AbstractBuilder abstractBuilder = BuilderFactory.create(dataMemberType);
                setter.invoke(abstractBuilder.build(inputValue));
            }

            return (T) objectInstance;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new EserialNoDefaultConstructorException(e);
        }
    }
}
