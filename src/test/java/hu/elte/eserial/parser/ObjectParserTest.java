package hu.elte.eserial.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ObjectParserTest {
    @Test
    public void parser_GivenAJsonWithSimpleObject_ReturnMap() {
       Map<String, Object> testMap = new ObjectParser().parser("{\"key1\": 2, \"key2\" : 5, \"key3\": \"value2\", \"key4\" : true}");

        Assert.assertTrue(4 == testMap.size());
        Assert.assertTrue(testMap.containsValue("value2"));
        Assert.assertTrue(testMap.containsKey("key1"));
        Assert.assertTrue(testMap.containsValue(new Long(5)));

        for (Map.Entry<String, Object> entry : testMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        Assert.assertTrue(testMap.containsValue(true));
    }

    @Test
    public void parser_GivenAJsonWithCompoundObject_ReturnMap() {
        Map<String, Object> testMap = new ObjectParser().parser("{\"key1\": 2, \"key2\" : 5, \"key3\": \"value3\", \"key4\" : true, \"key5\" : {\"key6\" : \"value6\"}}");

        Assert.assertTrue(5 == testMap.size());
    }

    @Test
    public void parser_GivenAJsonWithList_ReturnMap() {
        Map<String, Object> testMap = new ObjectParser().parser("{\"test\" : [\"test\":\"test\"]}");
    }
}