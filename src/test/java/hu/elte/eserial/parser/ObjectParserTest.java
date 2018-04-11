package hu.elte.eserial.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Map;

public class ObjectParserTest {
    @Test
    public void parser_GivenAJsonWithSimpleObject_ReturnMap() {
       Map<String, Object> testMap = new ObjectParser("{ \"key1\" : 2, \"key2\" : 5, \"key3\" : \"value2\" , \"key4\" : true, \"key5\":false, \"key6\" : null } ").parser();

        Assert.assertEquals(6, testMap.size());

        Assert.assertTrue(testMap.containsValue("value2"));
        Assert.assertTrue(testMap.containsKey("key1"));
        Assert.assertTrue(testMap.containsKey("key5"));
        Assert.assertTrue(testMap.containsKey("key6"));

        Assert.assertTrue(testMap.containsValue(new Long(5)));
        Assert.assertTrue(testMap.containsValue(true));
        Assert.assertTrue(testMap.containsValue(false));
        Assert.assertTrue(testMap.containsValue(null));
    }

    @Test
    public void parser_GivenAJsonWithCompoundObject_ReturnMap() {
        Map<String, Object> testMap = new ObjectParser("{\"key1\": 2.3, \"key2\" : 5, \"key3\": \"value3\" , \"key4\" : true, \"key5\" : {\"key6\" : \"value6\"}}").parser();

        Assert.assertEquals(5, testMap.size());
    }

    @Test
    public void parser_GivenAJsonWithList_ReturnMap() {
        Map<String, Object> testMap = new ObjectParser("{\"key1\" : [null , null], \"key2\" : null}").parser();

        Assert.assertEquals(2, testMap.size());

        Assert.assertTrue(testMap.containsValue(null));

       /* for (Map.Entry<String,Object> pair : testMap.entrySet()){
            System.out.println(pair.getKey()+" "+pair.getValue());
        }*/

       LinkedList<Object> list = (LinkedList) testMap.get("key1");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}