package hu.elte.eserial.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MapUtilsTest {

    @Test
    public void testGetDepth_GivenNull_Returns0 () {
        assertEquals(0, MapUtils.getDepth(null));
    }

    @Test
    public void testGetDepth_GivenEmptyMap_Returns0() {
        assertEquals(0, MapUtils.getDepth(new HashMap<>()));
    }

    @Test
    public void testGetDepth_GivenMapWithOneLevelAndOneProperty_Returns1() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        assertEquals(1, MapUtils.getDepth(map));
    }

    @Test
    public void testGetDepth_GivenMapWithOneLevelAndOneListProperty_Returns1() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", new ArrayList<Integer>() {{ add(45); }} );
        assertEquals(1, MapUtils.getDepth(map));
    }

    @Test
    public void testGetDepth_GivenMapWithOneLevelAndTwoProperties_Returns1() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        assertEquals(1, MapUtils.getDepth(map));
    }

    @Test
    public void testGetDepth_GivenMapWithTwoLevelsButNoPropertyInSecond_Returns1() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", new HashMap<>());
        assertEquals(1, MapUtils.getDepth(map));
    }

    @Test
    public void testGetDepth_GivenMapWithTwoLevels_Returns2() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("a", "a");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("c", "c");
        map1.put("b", map2);
        assertEquals(2, MapUtils.getDepth(map1));
    }

    @Test
    public void testGetDepth_GivenMapWithList_Returns1() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", new ArrayList<>());
        assertEquals(1, MapUtils.getDepth(map));
    }
}
