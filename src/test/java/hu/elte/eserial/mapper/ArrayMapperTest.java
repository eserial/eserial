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
    public void map_withNull() {
        mapper.map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_withInvalidType() {
        mapper.map(0);
    }

    @Test
    public void map_withEmpty() {
        Integer[] array = {};

        Object rootValue = mapper.map(array);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(array.length, root.size());
    }

    @Test
    public void map_withSomeElements() {
        Integer[] array = { 10, 20, 30 };

        Object rootValue = mapper.map(array);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(array.length, root.size());

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], root.get(i));
        }
    }
}