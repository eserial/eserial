package hu.elte.eserial.parser;

import hu.elte.eserial.exception.EserialInvalidJsonException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ObjectParserTest {
    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new ObjectParser(null).parse();
    }

    @Test(expected = EserialInvalidJsonException.class)
    public void serialize_GivenInvalidJsonWithoutOpeningCurlyBrackets_ThrowsEserialParserMismatchException() {
        new ObjectParser("\"key1\": 1]}").parse();
    }

    @Test(expected = EserialInvalidJsonException.class)
    public void serialize_GivenInvalidJsonWithoutColon_ThrowsEserialParserMismatchException() {
        new ObjectParser("{\"key1\" 1}").parse();
    }

    @Test(expected = EserialInvalidJsonException.class)
    public void serialize_GivenInvalidJsonObjectTheLastElementIsInInvalidFormat_ThrowsEserialParserMismatchException() {
        new ObjectParser("{\"key1\": 1, test}").parse();
    }

    @Test(expected = EserialInvalidJsonException.class)
    public void serialize_GivenInvalidJsonCommaAfterTheLastElement_ThrowsEserialParserMismatchException() {
        new ObjectParser("{\"key1\" : 1,}").parse();
    }

    @Test
    public void parser_GivenAJsonWithSimpleObject_ReturnMap() {
       Map<String, Object> testMap = new ObjectParser("{ \"key1\" : 2, \"key2\" : 5, \"key3\" : \"value2\" , \"key4\" : true, \"key5\":false, \"key6\" : null } ").parse();

        Assert.assertEquals(6, testMap.size());

        Assert.assertTrue(testMap.containsKey("key1"));
        Assert.assertTrue(testMap.containsKey("key2"));
        Assert.assertTrue(testMap.containsKey("key3"));
        Assert.assertTrue(testMap.containsKey("key4"));
        Assert.assertTrue(testMap.containsKey("key5"));
        Assert.assertTrue(testMap.containsKey("key6"));

        Assert.assertEquals(2L, testMap.get("key1"));
        Assert.assertEquals(5L, testMap.get("key2"));
        Assert.assertEquals("value2", testMap.get("key3"));
        Assert.assertEquals(true, testMap.get("key4"));
        Assert.assertEquals(false, testMap.get("key5"));
        Assert.assertEquals(null, testMap.get("key6"));
    }

    @Test
    public void parser_GivenAJsonWithCompoundObject_ReturnMap() {
        Map<String, Object> testMap = new ObjectParser("{\"key1\": 2.3, \"key2\" : 5, \"key3\": \"value3\" , \"key4\" : true, \"key5\" : {\"key6\" : \"value6\"}}").parse();

        Assert.assertEquals(5, testMap.size());

        Assert.assertEquals(HashMap.class, testMap.get("key5").getClass());

        HashMap<String, Object> hashMap = (HashMap) testMap.get("key5");

        Assert.assertEquals("value6", hashMap.get("key6"));
    }

    @Test
    public void parser_GivenAJsonWithList_ReturnMap() {
        Map<String, Object> testMap = new ObjectParser("{\"key1\" : [null , null], \"key2\" : null}").parse();

        Assert.assertEquals(2, testMap.size());

        Assert.assertTrue(testMap.containsValue(null));

        Assert.assertEquals(LinkedList.class, testMap.get("key1").getClass());

        LinkedList<Object> linkedList = (LinkedList) testMap.get("key1");

        Assert.assertEquals(null, linkedList.get(0));
        Assert.assertEquals(null, linkedList.get(1));
    }
}