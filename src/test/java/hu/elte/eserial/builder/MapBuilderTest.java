package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.Assert.assertEquals;

public class MapBuilderTest {

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        new MapBuilder(Boolean.class).build(new HashMap<>().put("key", "value"));
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidValue_ThrowsEserialBuilderMismatchException() {
        new MapBuilder(Map.class).build(new ArrayList<>().add("key"));
    }

    @Test
    public void build_GivenConcurrentNavigableMapInterface_ReturnsConcurrentSkipListMap() {
        MapBuilder mapBuilder = new MapBuilder(ConcurrentNavigableMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(ConcurrentSkipListMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenConcurrentMapInterface_ReturnsConcurrentHashMap() {
        MapBuilder mapBuilder = new MapBuilder(ConcurrentMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(ConcurrentHashMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenSortedMapInterface_ReturnsTreeMap() {
        MapBuilder mapBuilder = new MapBuilder(SortedMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(TreeMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenMapInterface_ReturnsHashMap() {
        MapBuilder mapBuilder = new MapBuilder(Map.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(HashMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenConcurrentHashMapClass_ReturnsConcurrentHashMap() {
        MapBuilder mapBuilder = new MapBuilder(ConcurrentHashMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(ConcurrentHashMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenConcurrentSkipListMap_ReturnsConcurrentSkipListMap() {
        MapBuilder mapBuilder = new MapBuilder(ConcurrentSkipListMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(ConcurrentSkipListMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenTreeMap_ReturnsTreeMap() {
        MapBuilder mapBuilder = new MapBuilder(TreeMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(TreeMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenLinkedHashMap_ReturnsLinkedHashMap() {
        MapBuilder mapBuilder = new MapBuilder(LinkedHashMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(LinkedHashMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenIdentityHashMap_ReturnsIdentityHashMap() {
        MapBuilder mapBuilder = new MapBuilder(IdentityHashMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(IdentityHashMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenWeakHashMap_ReturnsWeakHashMap() {
        MapBuilder mapBuilder = new MapBuilder(WeakHashMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(WeakHashMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }

    @Test
    public void build_GivenHashMap_ReturnsHashMap() {
        MapBuilder mapBuilder = new MapBuilder(HashMap.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        Map buildedMap = mapBuilder.build(hashMap);

        assertEquals(HashMap.class, buildedMap.getClass());
        assertEquals(1, buildedMap.size());
    }
}