package hu.elte.eserial.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

public class CollectionParserTest {
    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfNumbers_ReturnLongList() {
        LinkedList<Object> testList = new CollectionParser("[2, 3,4, 5 ]").parser();

        Assert.assertEquals(4, testList.size());

        Assert.assertEquals(testList.get(0), new Long(2));
        Assert.assertEquals(testList.get(1), new Long(3));
        Assert.assertEquals(testList.get(2), new Long(4));
        Assert.assertEquals(testList.get(3), new Long(5));
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfBooleans_ReturnBooleanList() {
        LinkedList<Object> testList = new CollectionParser("[true, false, true, false]").parser();

        Assert.assertEquals(4, testList.size());

        Assert.assertEquals(testList.get(0), true);
        Assert.assertEquals(testList.get(1), false);
        Assert.assertEquals(testList.get(2), true);
        Assert.assertEquals(testList.get(3), false);
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfStrings_ReturnStringList() {
        LinkedList<Object> testList = new CollectionParser("[\"test1 \", \"test2\", \"test3\", \"test4\"]").parser();

        Assert.assertEquals(4, testList.size());

        Assert.assertEquals(testList.get(0), "test1 ");
        Assert.assertEquals(testList.get(1), "test2");
        Assert.assertEquals(testList.get(2), "test3");
        Assert.assertEquals(testList.get(3), "test4");
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfObjects_ReturnObjectList() {
        LinkedList<Object> testList = new CollectionParser("[ {\"key1\" : \"value1\", \"key2\" : \"value2\"}, {\"key1\" : \"value1\", \"key2\" : \"value2\"}]").parser();

        Assert.assertTrue(2 == testList.size());

        Assert.assertEquals(testList.get(0).getClass(), HashMap.class);
        Assert.assertEquals(testList.get(1).getClass(), HashMap.class);
    }

    @Test
    public void parser_GivenAJsonWhichRepresentsAListOfLists_ReturnListList() {
        LinkedList<Object> testList = new CollectionParser("[ [\"key1\", \"value1\", \"key2\", \"value2\"], [\"key1\" , \"value1\", \"key2\" , \"value2\"]]").parser();

        Assert.assertTrue(2 == testList.size());

        Assert.assertEquals(testList.get(0).getClass(), LinkedList.class);
        Assert.assertEquals(testList.get(1).getClass(), LinkedList.class);
    }
}
