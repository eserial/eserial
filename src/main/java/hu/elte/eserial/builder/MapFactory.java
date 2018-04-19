package hu.elte.eserial.builder;

import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Provides an adequate {@link java.util.Map} implementation for a given {@link Type}.
 */
public class MapFactory {

    /**
     * Prevents the accidental instantiation of this factory class.
     */
    private MapFactory() {}

    /**
     * Creates a type-specific {@link Map} instance.
     *
     * @param mapClass an arbitrary {@link Class}
     * @return a {@link Map} instance
     * @throws InstantiationException if could not instantiate the non-interface {@link Map}
     * @throws IllegalAccessException if could not instantiate the non-interface {@link Map}
     */
    public static Map create(Class mapClass) throws InstantiationException, IllegalAccessException {
        Map mapObject;

        if (mapClass.isInterface()) {
            if (TypeUtils.isAssignableFrom(mapClass, ConcurrentNavigableMap.class)) {
                mapObject = new ConcurrentSkipListMap();
            } else if (TypeUtils.isAssignableFrom(mapClass, ConcurrentMap.class)) {
                mapObject = new ConcurrentHashMap();
            } else if (TypeUtils.isAssignableFrom(mapClass, SortedMap.class)) {
                mapObject = new TreeMap();
            } else {
                mapObject = new HashMap();
            }
        } else {
            mapObject = (Map) mapClass.newInstance();
        }

        return mapObject;
    }
}
