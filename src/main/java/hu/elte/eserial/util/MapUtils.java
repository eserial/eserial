package hu.elte.eserial.util;

import java.util.Comparator;
import java.util.Map;

public class MapUtils {

    /**
     * Calculates the depth of a map. <br>
     * These values don't add to the depth of the map:
     * <ul>
     *     <li>{@code} null</li>
     *     <li>an empty map</li>
     *     <li>a "simple object" meaning it can be serialized inline</li>
     * </ul>
     *
     * Examples:
     * <ul>
     *     <li>getDepth(null) == 0</li>
     *     <li>getDepth({}) == 0</li>
     *     <li>getDepth({a: "a"}) == 1</li>
     *     <li>getDepth({a: "a", b: {}}) == 1</li>
     *     <li>getDepth({a: "a", b: []}9 == 1</li>
     *     <li>getDepth({a: "a", b: {c: "c"}}) == 2</li>
     * </ul>

     *
     * @param map the map
     * @return the depth of the map according to the rules above
     *
     * @see TypeUtils#isSimpleType(Class)
     */
    public static int getDepth(Map<String, Object> map) {
        if (map == null) {
            return 0;
        }
        return getDepth(map, 0);
    }

    private static int getDepth(Object obj, int height) {
        if (!(obj instanceof Map)) {
            return height;
        }
        else {
            Map<String, Object> map = (Map)obj;
            Integer maxDepth = map.keySet()
                    .stream()
                    .map(key -> getDepth(map.get(key), height))
                    .max(Comparator.naturalOrder())
                    .orElse(-1);
            return maxDepth + 1;
        }
    }
}
