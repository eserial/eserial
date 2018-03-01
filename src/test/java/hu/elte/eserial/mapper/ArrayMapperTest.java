package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayMapperTest {

    private ArrayMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ArrayMapper();
    }

    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        mapper.map(null, null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        mapper.map(0, null);
    }

    @Test
    public void map_GivenAnEmptyArray_ReturnsEmptyList() {
        Integer[] array = {};

        Object rootValue = mapper.map(array, null);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(array.length, root.size());
    }

    @Test
    public void map_GivenSomeElements_ReturnsListContainingTheseElements() {
        Integer[] array = { 10, 20, 30 };

        Object rootValue = mapper.map(array, null);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(array.length, root.size());

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], root.get(i));
        }
    }
}