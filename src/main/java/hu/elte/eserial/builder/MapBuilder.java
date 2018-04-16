package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
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
     * @return a map of the given {@link Class} and initialized from the {@link LinkedList} parameter
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
            throw new EserialBuilderMismatchException(Map.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            List<Map<String, Object>> initializationList = (List<Map<String, Object>>) initializationObject;
            Map<Object, Object> initializationMap = new HashMap<>();

            for (Map<String, Object> map : initializationList) {
                Object keyObject = map.get("key");
                Object valueObject = map.get("value");

                if (keyType == null && valueType == null) {
                    initializationMap.put(keyObject, valueObject);
                } else {
                    AbstractBuilder keyBuilder = BuilderFactory.create(keyType);
                    AbstractBuilder valueBuilder = BuilderFactory.create(valueType);

                    Object builtKey = keyBuilder.build(keyObject);
                    Object builtValue = valueBuilder.build(valueObject);

                    initializationMap.put(builtKey, builtValue);
                }
            }

            Map mapObject;

            if (typeClass.isInterface()) {
                if (TypeUtils.isAssignableFrom(typeClass, ConcurrentNavigableMap.class)) {
                    mapObject = new ConcurrentSkipListMap();
                } else if (TypeUtils.isAssignableFrom(typeClass, ConcurrentMap.class)) {
                    mapObject = new ConcurrentHashMap();
                } else if (TypeUtils.isAssignableFrom(typeClass, SortedMap.class)) {
                    mapObject = new TreeMap();
                } else {
                    mapObject = new HashMap();
                }
            } else {
                mapObject = (Map) typeClass.newInstance();
            }

            mapObject.putAll(initializationMap);

            return (T) mapObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize map", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize map", e);
        }
    }
}
