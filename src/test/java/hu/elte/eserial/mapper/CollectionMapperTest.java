package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectionMapperTest {

    private CollectionMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new CollectionMapper();
    }

    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        mapper.map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        mapper.map(0);
    }

    @Test
    public void map_GivenAnEmptyArray_ReturnsEmptyList() {
        Set<Integer> set = new HashSet<Integer>();

        Object rootValue = mapper.map(set);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(set.size(), root.size());
    }

    @Test
    public void map_GivenSomeElements_ReturnsListContainingTheseElements() {
        Set<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        Object rootValue = mapper.map(set);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(set.size(), root.size());

        for (Integer integer : set) {
            assertTrue(root.contains(integer));
        }
    }

    @Test
    public void map_GivenSomeElements_ShouldPreserveOrder() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        Object rootValue = mapper.map(list);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(list.size(), root.size());

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), root.get(i));
        }
    }
}