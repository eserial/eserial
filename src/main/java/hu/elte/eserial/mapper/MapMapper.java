package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Maps Map-like objects (e.g Map, HashMap).
 */
public class MapMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the map {@code that}.
     *
     * @param that a map
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that) {
        if (!TypeUtils.isMap(that.getClass())) {
            throw new EserialMapperMismatchException(that.getClass());
        }

        Map map = (Map) that;

        return map
                .entrySet()
                .stream()
                .map(entryObject -> {
                    Map.Entry entry = (Map.Entry) entryObject;

                    Map<String, Object> entryMap = new HashMap<>();
                    entryMap.put("key", entry.getKey());
                    entryMap.put("value", entry.getValue());

                    return entryMap;
                })
                .collect(Collectors.toList());
    }
}
