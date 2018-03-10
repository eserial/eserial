package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.model.EserialContext;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MapMapperTest {

    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        new MapMapper(null).map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        new MapMapper(0).map(null);
    }

    @Test
    public void map_GivenAnEmptyMap_ReturnsAnEmptyList() {
        Map<String, Integer> map = new HashMap<>();

        Object rootValue = new MapMapper(map).map(EserialContext.forRoot(map));
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(map.size(), root.size());
    }

    @Test
    public void map_GivenSomeElements_ReturnsListOfKeysAndValues() {
        Map<String, Integer> map = new HashMap<>();
        map.put("firstId", 1);
        map.put("secondId", 2);

        Object rootValue = new MapMapper(map).map(EserialContext.forRoot(map));
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(map.size(), root.size());

        assertEquals(1, getByKey(root, "firstId"));
        assertEquals(2, getByKey(root, "secondId"));
    }

    @Test
    public void map_GivenDirectlyRecursiveObjects_StopsRecursion() {
        Map<String, Object> user = new HashMap<>();
        Map<String, Object> token = new HashMap<>();
        user.put("token", token);
        token.put("user", user);

        Object rootValue = new MapMapper(user).map(EserialContext.forRoot(user));
        assertTrue(rootValue instanceof List);

        List root = (List) rootValue;
        List tokenRoot = (List) getByKey(root, "token");

        assertNull(getByKey(tokenRoot, "token"));
    }

    @Test
    public void map_GivenCircularRecursiveObject_StopsRecursion() {
        Map<String, Object> first = new HashMap<>();
        Map<String, Object> second = new HashMap<>();
        Map<String, Object> last = new HashMap<>();
        first.put("next", second);
        second.put("next", last);
        last.put("next", first);

        Object rootValue = new MapMapper(first).map(EserialContext.forRoot(first));
        assertTrue(rootValue instanceof List);

        List firstRoot = (List) rootValue;
        List secondRoot = (List) getByKey(firstRoot, "next");
        List lastRoot = (List) getByKey(secondRoot, "next");

        assertNull(getByKey(lastRoot, "next"));
    }

    @Test
    public void map_GivenRecursionInCollectionWithRootInCollection_StopsRecursion() {
        Map<String, Object> edge12 = new HashMap<>();
        edge12.put("edgeName", "12");
        Map<String, Object> node1 = new HashMap<>();
        node1.put("nodeName", "1");
        Map<String, Object> node2 = new HashMap<>();
        node2.put("nodeName", "2");

        edge12.put("node1", node1);
        edge12.put("node2", node2);
        List<Map<String, Object>> node1edges1 = new ArrayList<>();
        node1edges1.add(edge12);
        node1.put("edges1", node1edges1);
        List<Map<String, Object>> node2edges2 = new ArrayList<>();
        node2edges2.add(edge12);
        node2.put("edges2", node2edges2);

        //Would throw a StackOverflowError on infinite recursion.
        new MapMapper(edge12).map(EserialContext.forRoot(edge12));
    }

    @Test
    public void map_GivenRecursionInCollectionWithRootOutsideCollection_StopsRecursion() {
        Map<String, Object> edge12 = new HashMap<>();
        edge12.put("edgeName", "12");
        Map<String, Object> node1 = new HashMap<>();
        node1.put("nodeName", "1");
        Map<String, Object> node2 = new HashMap<>();
        node2.put("nodeName", "2");

        edge12.put("node1", node1);
        edge12.put("node2", node2);
        List<Map<String, Object>> node1edges1 = new ArrayList<>();
        node1edges1.add(edge12);
        node1.put("edges1", node1edges1);
        List<Map<String, Object>> node2edges2 = new ArrayList<>();
        node2edges2.add(edge12);
        node2.put("edges2", node2edges2);

        //Would throw a StackOverflowError on infinite recursion.
        new MapMapper(node1).map(EserialContext.forRoot(node1));
    }

    private Object getByKey(List list, Object key) {
        for (Object entryObject : list) {
            Map entryMap = (Map) entryObject;
            if (entryMap.get("key").equals(key)) {
                return entryMap.get("value");
            }
        }
        return null;
    }
}
