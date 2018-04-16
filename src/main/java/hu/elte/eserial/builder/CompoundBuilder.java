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
            Class classOfObjectType = TypeUtils.convertTypeToClass(type);

            Object objectInstance = classOfObjectType.newInstance();

            for (Method method : classOfObjectType.getMethods()) {
                if (MethodUtils.isIgnored(method) || !MethodUtils.isSetter(method)) {
                    continue;
                }

                Map<String, Object> initializationMap = (Map) initializationObject;

                Setter setter = new Setter(objectInstance, method);

                String nameOfActualDataMember = setter.getElementName();
                Object valueOfActualDataMember = initializationMap.get(nameOfActualDataMember);

                Type typeOfActualDataMember = setter.getTypeOfSetterParameter();

                AbstractBuilder abstractBuilder = BuilderFactory.create(typeOfActualDataMember);
                setter.invoke(abstractBuilder.build(valueOfActualDataMember));
            }

            return (T) objectInstance;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new EserialNoDefaultConstructorException("Could not find default constructor", e);
        }
    }
}
