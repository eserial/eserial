package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.TypeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Build Map objects.
 */
public class MapBuilder extends AbstractBuilder{

    /**
     * Constructs an {@link MapBuilder} and sets the {@code type} in it.
     *
     * @param type {@link Map} class to be used in the {@link AbstractBuilder#build} method
     */
    MapBuilder(Class type) {
        super(type);
    }

    /**
     * @param value {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a map of the given class and initialized with the map parameter
     */
    @Override
    public <T> T build(Object value) {
        if (value == null) {
            return null;
        }

        if (!TypeUtils.isMap(type) || !TypeUtils.isMap(value.getClass())) {
            throw new EserialBuilderMismatchException(Map.class.getSimpleName(), type.getName());
        }

        try {
            Map<Object, Object> map = (Map<Object, Object>) value;

            if (type.isInterface()) {
                if (TypeUtils.isConcurrentNavigableMap(type)) {
                    return (T) new ConcurrentSkipListMap(map);
                } else if (TypeUtils.isConcurrentMap(type)) {
                    return (T) new ConcurrentHashMap(map);
                } else if (TypeUtils.isSortedMap(type)) {
                    return (T) new TreeMap(map);
                } else {
                    return (T) new HashMap(map);
                }
            }

            Map mapObject = (Map) type.newInstance();
            mapObject.putAll(map);

            return (T) mapObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException(e.getMessage());
        } catch (InstantiationException e) {
            throw new EserialInstantiationException(e.getMessage());
        }
    }
}
