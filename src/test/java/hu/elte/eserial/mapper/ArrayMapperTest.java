package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayMapperTest {


    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        new ArrayMapper(null).map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        new ArrayMapper(0).map(null);
    }

    @Test
    public void map_GivenAnEmptyArray_ReturnsEmptyList() {
        Integer[] array = {};

        Object rootValue = new ArrayMapper(array).map(null);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(array.length, root.size());
    }

    @Test
    public void map_GivenSomeElements_ReturnsListContainingTheseElements() {
        Integer[] array = { 10, 20, 30 };

        Object rootValue = new ArrayMapper(array).map(null);
        assertTrue(rootValue instanceof List);

        List<Object> root = (List) rootValue;
        assertEquals(array.length, root.size());

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], root.get(i));
        }
    }
}