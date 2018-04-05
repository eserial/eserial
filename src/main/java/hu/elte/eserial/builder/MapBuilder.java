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
 * Build Map objects.
 */
public class MapBuilder extends AbstractBuilder{

    /**
     * Constructs an {@link MapBuilder} and sets the {@code type} in it.
     *
     * @param type {@link Map} class to be used in the {@link AbstractBuilder#build} method
     */
    MapBuilder(Type type) {
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

        Class clazz = TypeUtils.convertTypeToClass(type);
        Type keyTypeArg = TypeUtils.getTypeArgument(type, 0);
        Type valueTypeArg = TypeUtils.getTypeArgument(type, 1);

        if (!TypeUtils.isMap(clazz) || !TypeUtils.isMap(value.getClass())) {
            throw new EserialBuilderMismatchException(Map.class.getSimpleName(), clazz.getName());
        }

        try {
            Map<Object, Object> map = (Map<Object, Object>) value;

            Class keyTypeArgClass = TypeUtils.convertTypeToClass(keyTypeArg);
            Class valueTypeArgsClass = TypeUtils.convertTypeToClass(valueTypeArg);

            if (keyTypeArg != null && valueTypeArg != null) {
                Map<Object, Object> newMap = new HashMap<>();
                Iterator it = map.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();

                    Object keyObject;
                    if(TypeUtils.isCompound(keyTypeArgClass)) {
                        AbstractBuilder abstractBuilder = BuilderFactory.create(keyTypeArg);
                        keyObject = abstractBuilder.build(pair.getKey());
                    } else {
                        keyObject = pair.getKey();
                    }

                    Object valueObject;
                    if(TypeUtils.isCompound(valueTypeArgsClass)) {
                        AbstractBuilder abstractBuilder = BuilderFactory.create(valueTypeArg);
                        valueObject = abstractBuilder.build(pair.getValue());
                    } else {
                        valueObject = pair.getValue();
                    }

                    newMap.put(keyObject, valueObject);
                }

                map = newMap;
            }

            if (clazz.isInterface()) {
                if (TypeUtils.isConcurrentNavigableMap(clazz)) {
                    return (T) new ConcurrentSkipListMap(map);
                } else if (TypeUtils.isConcurrentMap(clazz)) {
                    return (T) new ConcurrentHashMap(map);
                } else if (TypeUtils.isSortedMap(clazz)) {
                    return (T) new TreeMap(map);
                } else {
                    return (T) new HashMap(map);
                }
            }

            Map mapObject = (Map) clazz.newInstance();
            mapObject.putAll(map);

            return (T) mapObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize map", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize map", e);
        }
    }
}
