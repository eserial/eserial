package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapMapperTest {

    private MapMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new MapMapper();
    }

    @Test(expected = NullPointerException.class)
    public void map_withNull() {
        mapper.map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_withInvalidType() {
        mapper.map(0);
    }

    @Test
    public void map_withEmpty() {
        Map<String, Integer> map = new HashMap<>();

        Object rootValue = mapper.map(map);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(map.size(), root.size());
    }

    @Test
    public void map_withSomeElements() {
        Map<String, Integer> map = new HashMap<>();
        map.put("firstId", 1);
        map.put("secondId", 2);

        Object rootValue = mapper.map(map);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(map.size(), root.size());

        Map<String, Object> first = (Map) root.get(1);
        assertEquals("firstId", first.get("key"));
        assertEquals(1, first.get("value"));

        Map<String, Object> second = (Map) root.get(0);
        assertEquals("secondId", second.get("key"));
        assertEquals(2, second.get("value"));
    }
}