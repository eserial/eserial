package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
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
     * @return a map of the given class and initialized with the map parameter
     */
    @Override
    public <T> T build(Object initializationObject) {
        if (initializationObject == null) {
            return null;
        }

        Class classOfMapType = TypeUtils.convertTypeToClass(type);
        Type typeOfMapKeyTypeArgument = TypeUtils.getTypeArgument(type, 0);
        Type typeOfMapValueTypeArgument = TypeUtils.getTypeArgument(type, 1);

        if (!TypeUtils.isMap(classOfMapType)) {
            throw new EserialBuilderMismatchException(Map.class.getSimpleName(), classOfMapType.getName());
        }

        if (!TypeUtils.isMap(initializationObject.getClass())) {
            throw new EserialBuilderMismatchException(Map.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            Map<Object, Object> initializationMap = (Map<Object, Object>) initializationObject;

            Class classOfKeyTypeArgumentType = TypeUtils.convertTypeToClass(typeOfMapKeyTypeArgument);
            Class classOfValueTypeArgumentType = TypeUtils.convertTypeToClass(typeOfMapValueTypeArgument);

            if (typeOfMapKeyTypeArgument != null && typeOfMapValueTypeArgument != null) {
                Map<Object, Object> newMap = new HashMap<>();
                Iterator it = initializationMap.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();

                    Object keyObject;
                    if(TypeUtils.isCompound(classOfKeyTypeArgumentType)) {
                        AbstractBuilder abstractBuilder = BuilderFactory.create(typeOfMapKeyTypeArgument);
                        keyObject = abstractBuilder.build(pair.getKey());
                    } else {
                        keyObject = pair.getKey();
                    }

                    Object valueObject;
                    if(TypeUtils.isCompound(classOfValueTypeArgumentType)) {
                        AbstractBuilder abstractBuilder = BuilderFactory.create(typeOfMapValueTypeArgument);
                        valueObject = abstractBuilder.build(pair.getValue());
                    } else {
                        valueObject = pair.getValue();
                    }

                    newMap.put(keyObject, valueObject);
                }

                initializationMap = newMap;
            }

            if (classOfMapType.isInterface()) {
                if (TypeUtils.isConcurrentNavigableMap(classOfMapType)) {
                    return (T) new ConcurrentSkipListMap(initializationMap);
                } else if (TypeUtils.isConcurrentMap(classOfMapType)) {
                    return (T) new ConcurrentHashMap(initializationMap);
                } else if (TypeUtils.isSortedMap(classOfMapType)) {
                    return (T) new TreeMap(initializationMap);
                } else {
                    return (T) new HashMap(initializationMap);
                }
            }

            Map mapObject = (Map) classOfMapType.newInstance();
            mapObject.putAll(initializationMap);

            return (T) mapObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize map", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize map", e);
        }
    }
}
