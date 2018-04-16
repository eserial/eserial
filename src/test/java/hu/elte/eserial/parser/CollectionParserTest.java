package hu.elte.eserial.parser;

import hu.elte.eserial.exception.EserialInvalidJsonException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class CollectionParserTest {
    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new CollectionParser(null).parse();
    }

    @Test(expected = EserialInvalidJsonException.class)
    public void serialize_GivenInvalidJsonWithoutOpeningCurlyBrackets_ThrowsEserialParserMismatchException() {
        new CollectionParser("\"key1\": 1]").parse();
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfNumbers_ReturnLongList() {
        LinkedList<Object> testList = new CollectionParser("[2, 3,4, 5 ]").parse();

        Assert.assertEquals(4, testList.size());

        Assert.assertEquals(Arrays.asList(2L, 3L, 4L, 5L), testList);
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfBooleans_ReturnBooleanList() {
        LinkedList<Object> testList = new CollectionParser("[true, false, true, false]").parse();

        Assert.assertEquals(4, testList.size());

        Assert.assertEquals(true, testList.get(0));
        Assert.assertEquals(false, testList.get(1));
        Assert.assertEquals(true, testList.get(2));
        Assert.assertEquals(false, testList.get(3));
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfStrings_ReturnStringList() {
        LinkedList<Object> testList = new CollectionParser("[\"test1 \", \"test2\", \"test3\", \"test4\"]").parse();

        Assert.assertEquals(4, testList.size());

        Assert.assertEquals("test1 ", testList.get(0));
        Assert.assertEquals("test2", testList.get(1));
        Assert.assertEquals("test3", testList.get(2));
        Assert.assertEquals("test4", testList.get(3));
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfObjects_ReturnObjectList() {
        LinkedList<Object> testList = new CollectionParser("[ {\"key1\" : \"value1\", \"key2\" : \"value2\"}, {\"key1\" : \"value1\", \"key2\" : \"value2\"}]").parse();

        Assert.assertEquals(2, testList.size());

        Assert.assertEquals(HashMap.class, testList.get(0).getClass());
        Assert.assertEquals(HashMap.class, testList.get(1).getClass());
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfLists_ReturnListList() {
        LinkedList<Object> testList = new CollectionParser("[ [\"key1\", \"value1\", \"key2\", \"value2\"], [\"key1\" , \"value1\", \"key2\" , \"value2\"]]").parse();

        Assert.assertEquals(2, testList.size());

        Assert.assertEquals(LinkedList.class, testList.get(0).getClass());
        Assert.assertEquals(LinkedList.class, testList.get(1).getClass());
    }
}
