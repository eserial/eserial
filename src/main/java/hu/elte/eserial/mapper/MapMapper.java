package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.util.TypeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Maps Map-like objects (e.g Map, HashMap).
 */
public class MapMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the map {@code that}.
     *
     * @param {@code that} a map
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of {@code that}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object map(Object that, RecursionChecker recursionChecker) {
        if (!TypeUtils.isMap(that.getClass())) {
            throw new EserialMapperMismatchException(Map.class.getSimpleName(), that.getClass().getSimpleName());
        }

        Map map = (Map) that;
        if (recursionChecker == null) {
            recursionChecker = new RecursionChecker(that);
        }

        List<Map<String, Object>> mappedEntries = new ArrayList<>();
        for (Object entryObject: map.entrySet()) {
          Map.Entry entry = (Map.Entry) entryObject;

          Object key = entry.getKey();
          Object mappedKey = null;

          Object value = entry.getValue();
          Object mappedValue = null;

          EserialElement keyElement = new EserialElement(null, key);
          if (recursionChecker.canVisit(keyElement)) {
            recursionChecker.beforeVisit(keyElement);
            mappedKey = MapperFactory.create(key.getClass()).map(key, recursionChecker);
            recursionChecker.afterVisit(keyElement);
          }

          EserialElement valueElement = new EserialElement(null, value);
          if (recursionChecker.canVisit(valueElement)) {
              recursionChecker.beforeVisit(valueElement);
              mappedValue = MapperFactory.create(value.getClass()).map(value, recursionChecker);
              recursionChecker.afterVisit(valueElement);
          }

          if (mappedKey != null && mappedValue != null) {
            Map<String, Object> entryMap = new HashMap<>();
            entryMap.put("key", mappedKey);
            entryMap.put("value", mappedValue);

            mappedEntries.add(entryMap);
          }
        }

        return mappedEntries;
    }
}
