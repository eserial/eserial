package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.TypeUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapBuilder {

    public static Map<Object, Object> build(Object value, Class type) throws Exception{
        if (!TypeUtils.isMap(type) || !TypeUtils.isMap(value.getClass())) {
            throw new EserialException("Type mismatch");
        }

        Map<Object, Object> map = (Map<Object, Object>) value;

        if(type.isInterface()) {
            if (TypeUtils.isConcurrentNavigableMap(type)) {
                return new ConcurrentSkipListMap(map);
            } else if (TypeUtils.isConcurrentMap(type)) {
                return new ConcurrentHashMap(map);
            } else if (TypeUtils.isSortedMap(type)) {
                return new TreeMap(map);
            } else {
                return new HashMap(map);
            }
        }

        Map mapObject = (Map) type.newInstance();
        mapObject.putAll(map);

        return mapObject;
    }
}
