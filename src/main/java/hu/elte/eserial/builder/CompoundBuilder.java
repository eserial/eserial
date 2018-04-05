package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.model.Setter;
import hu.elte.eserial.util.MethodUtils;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Builds Compound objects.
 */
public class CompoundBuilder extends AbstractBuilder {

    /**
     * Constructs a {@link CompoundBuilder} and sets the {@code type} in it.
     *
     * @param type the class to be used in the {@link AbstractBuilder#build} method
     */
    CompoundBuilder(Type type) {
        super(type);
    }

    /**
     * @param value {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a compound object of the given class and initialized with the map parameter
     */
    @Override
    public <T> T build(Object value) {
        if (value == null) {
            return null;
        }

        try {
            Class clazz = TypeUtils.convertTypeToClass(type);

            Object that = clazz.newInstance();

            for (Method method : clazz.getMethods()) {
                if (MethodUtils.isIgnored(method) || !MethodUtils.isSetter(method)) {
                    continue;
                }

                Map<String, Object> map = (Map) value;

                Setter setter = new Setter(that, method);

                String fieldName = setter.getElementName();
                Object fieldValue = map.get(fieldName);

                Type paramType = setter.getTypeParameter();

                AbstractBuilder abstractBuilder = BuilderFactory.create(paramType);
                setter.evaluate(abstractBuilder.build(fieldValue));
            }

            return (T) that;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize object", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize object", e);
        }
    }
}
