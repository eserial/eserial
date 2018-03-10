package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.util.TypeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maps Map-like objects (e.g Map, HashMap).
 */
public class MapMapper extends AbstractMapper {

    /**
     * Constructs a {@link MapMapper} and sets the {@code object} in it.
     *
     * @param object the {@link Map} to be used in the {@link AbstractMapper#map} method
     */
    MapMapper(Object object) {
        super(object);
    }

    /**
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of the contained {@link Enum}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object map(RecursionChecker recursionChecker) {
        if (!TypeUtils.isMap(this.object.getClass())) {
            throw new EserialMapperMismatchException(Map.class.getSimpleName(), this.object.getClass().getSimpleName());
        }

        Map map = (Map) this.object;
        if (recursionChecker == null) {
            recursionChecker = new RecursionChecker(this.object);
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
            mappedKey = MapperFactory.create(key).map(recursionChecker);
            recursionChecker.afterVisit(keyElement);
          }

          EserialElement valueElement = new EserialElement(null, value);
          if (recursionChecker.canVisit(valueElement)) {
              recursionChecker.beforeVisit(valueElement);
              mappedValue = MapperFactory.create(value).map(recursionChecker);
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
