package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.model.Setter;
import hu.elte.eserial.util.MethodUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Builds Compound objects.
 */
public class CompoundBuilder extends AbstractBuilder {

    /**
     * Constructs an {@link CompoundBuilder} and sets the {@code type} in it.
     *
     * @param type the class to be used in the {@link AbstractBuilder#build} method
     */
    CompoundBuilder(Class type) {
        super(type);
    }

    /**
     * @param value {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a compuund object of the given class and initialized with the map parameter
     */
    @Override
    public <T> T build(Object value) {
        if (value == null) {
            return null;
        }

        try {
            Object that = type.newInstance();

            for (Method method : type.getMethods()) {
                if (MethodUtils.isIgnored(method) || !MethodUtils.isSetter(method)) {
                    continue;
                }

                Map<String, Object> map = (Map) value;

                Setter setter = new Setter(that, method);

                String fieldName = setter.getElementName();
                Object fieldValue = map.get(fieldName);
                Class<?> paramType = setter.getParameterType();

                AbstractBuilder abstractBuilder = BuilderFactory.create(paramType);
                setter.evaluate(abstractBuilder.build(fieldValue));
            }

            return (T) that;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException(e.getMessage());
        } catch (InstantiationException e) {
            throw new EserialInstantiationException(e.getMessage());
        }
    }
}
