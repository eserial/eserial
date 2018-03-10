package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArraySerializerTest {

    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new ArraySerializer(null).serialize();
    }

    @Test(expected = EserialSerializerMismatchException.class)
    public void serialize_GivenInvalidType_ThrowsEserialSerializerMismatchException() {
        new ArraySerializer(true).serialize();
    }

    @Test
    public void serialize_GivenEmptyList_ReturnsEmptyJsonArray() {
        List list = new ArrayList<>();
        assertEquals("[]", new ArraySerializer(list).serialize());
    }

    @Test
    public void serialize_GivenAListOfNumbers_ReturnsJsonArrayWithNumbers() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals("[1,2,3]", new ArraySerializer(list).serialize());
    }

    @Test
    public void serialize_GivenAListOfList_ReturnsJsonArrayOfArray() {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(1);
        list.get(0).add(2);
        list.get(0).add(3);

        assertEquals("[[1,2,3]]", new ArraySerializer(list).serialize());
    }
}