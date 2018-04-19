package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Builds Map objects.
 */
public class MapBuilder extends AbstractBuilder{
    private final String KEY = "key";
    private final String VALUE = "value";

    /**
     * Constructs a {@link MapBuilder} and sets the {@code mapType} in it.
     *
     * @param mapType {@link Map} class to be used in the {@link AbstractBuilder#build} method
     */
    MapBuilder(Type mapType) {
        super(mapType);
    }

    /**
     * @param initializationObject {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a map of the given {@link Type} and initialized from the {@link LinkedList} parameter
     */
    @Override
    public <T> T build(Object initializationObject) {
        if (initializationObject == null) {
            return null;
        }

        Class typeClass = TypeUtils.convertTypeToClass(type);
        Type keyType = TypeUtils.getTypeArgument(type, 0);
        Type valueType = TypeUtils.getTypeArgument(type, 1);

        if (!TypeUtils.isAssignableFrom(typeClass, Map.class)) {
            throw new EserialBuilderMismatchException(Map.class.getSimpleName(), typeClass.getName());
        }

        if (!TypeUtils.isAssignableFrom(initializationObject.getClass(), List.class)) {
            throw new EserialInputTypeMismatchException(initializationObject.getClass().getName());
        }

        try {
            List<Map<String, Object>> initializationList = (List<Map<String, Object>>) initializationObject;
            Map<Object, Object> builtMap = MapFactory.create(typeClass);

            if(keyType == null && valueType == null) {
                for (Map<String, Object> map : initializationList) {
                    builtMap.put(map.get(KEY), map.get(VALUE));
                }
            } else {
                AbstractBuilder keyBuilder = BuilderFactory.create(keyType);
                AbstractBuilder valueBuilder = BuilderFactory.create(valueType);

                for (Map<String, Object> map : initializationList) {
                    Object builtKey = keyBuilder.build(map.get(KEY));
                    Object builtValue = valueBuilder.build(map.get(VALUE));

                    builtMap.put(builtKey, builtValue);
                }
            }

            return (T) builtMap;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize map", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize map", e);
        }
    }
}
