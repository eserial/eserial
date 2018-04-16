package hu.elte.eserial.builder;

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
     * @return a compound object of the given {@link Class} and initialized with the {@link Map} parameter
     */
    @Override
    public <T> T build(Object initializationObject) {
        if (initializationObject == null) {
            return null;
        }

        try {
            Class typeClass = TypeUtils.convertTypeToClass(type);

            Object objectInstance = typeClass.newInstance();

            for (Method method : typeClass.getMethods()) {
                if (MethodUtils.isIgnored(method) || !MethodUtils.isSetter(method)) {
                    continue;
                }

                Map<String, Object> initializationMap = (Map) initializationObject;

                Setter setter = new Setter(objectInstance, method);

                String dataMemberName = setter.getElementName();
                Object dataMemberValue = initializationMap.get(dataMemberName);

                Type dataMemberType = setter.getTypeOfSetterParameter();

                AbstractBuilder abstractBuilder = BuilderFactory.create(dataMemberType);
                setter.invoke(abstractBuilder.build(dataMemberValue));
            }

            return (T) objectInstance;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new EserialNoDefaultConstructorException("Could not find default constructor", e);
        }
    }
}
