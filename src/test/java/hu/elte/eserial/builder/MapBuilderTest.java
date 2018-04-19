package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import org.junit.Test;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MapBuilderTest {

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("key", "string");
        map.put("value", "text");

        mapList.add(map);

        new MapBuilder(Boolean.class).build(mapList, null);
    }

    @Test(expected = EserialInputTypeMismatchException.class)
    public void build_GivenInvalidValue_ThrowsEserialInputTypeMismatchException() {
        new MapBuilder(Map.class).build(new Boolean("true"), null);
    }

    @Test
    public void build_GivenMapNullValue_ReturnsNullValue() {
        assertNull(new MapBuilder(Map.class).build(null, null));
    }

    @Test
    public void build_GivenConcurrentNavigableMapInterface_ReturnsConcurrentSkipListMap() {
        MapBuilder mapBuilder = new MapBuilder(ConcurrentNavigableMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", "string");
        map1.put("value", "text");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", "string2");
        map2.put("value", "text2");

        mapList.add(map1);
        mapList.add(map2);

        ConcurrentSkipListMap builtMap = mapBuilder.build(mapList, null);

        assertEquals(ConcurrentSkipListMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals("text", builtMap.get("string"));
        assertEquals("text2", builtMap.get("string2"));
    }

    @Test
    public void build_GivenConcurrentMapInterface_ReturnsConcurrentHashMap() {
        MapBuilder mapBuilder = new MapBuilder(ConcurrentMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(ConcurrentHashMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenSortedMapInterface_ReturnsTreeMap() {
        MapBuilder mapBuilder = new MapBuilder(SortedMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(TreeMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenMapInterface_ReturnsHashMap() {
        MapBuilder mapBuilder = new MapBuilder(Map.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(HashMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenConcurrentHashMapClass_ReturnsConcurrentHashMap() {
        MapBuilder mapBuilder = new MapBuilder(ConcurrentHashMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(ConcurrentHashMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenConcurrentSkipListMap_ReturnsConcurrentSkipListMap() {
        MapBuilder mapBuilder = new MapBuilder(ConcurrentSkipListMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(ConcurrentSkipListMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenTreeMap_ReturnsTreeMap() {
        MapBuilder mapBuilder = new MapBuilder(TreeMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(TreeMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenLinkedHashMap_ReturnsLinkedHashMap() {
        MapBuilder mapBuilder = new MapBuilder(LinkedHashMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(LinkedHashMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenIdentityHashMap_ReturnsIdentityHashMap() {
        MapBuilder mapBuilder = new MapBuilder(IdentityHashMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(IdentityHashMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenWeakHashMap_ReturnsWeakHashMap() {
        MapBuilder mapBuilder = new MapBuilder(WeakHashMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(WeakHashMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }

    @Test
    public void build_GivenHashMap_ReturnsHashMap() {
        MapBuilder mapBuilder = new MapBuilder(HashMap.class);
        List<Map<String, Object>> mapList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", 1L);
        map1.put("value", 2L);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key", 3L);
        map2.put("value", 4L);

        mapList.add(map1);
        mapList.add(map2);

        Map builtMap = mapBuilder.build(mapList, null);

        assertEquals(HashMap.class, builtMap.getClass());
        assertEquals(2, builtMap.size());
        assertEquals(2L, builtMap.get(1L));
        assertEquals(4L, builtMap.get(3L));
    }
}
