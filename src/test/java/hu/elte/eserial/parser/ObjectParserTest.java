package hu.elte.eserial.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ObjectParserTest {
    @Test
    public void parser_GivenASimpleObject() {
       Map<String, Object> testMap = new ObjectParser().parser("{\"car\": 2, \"house\" : 5, \"name\": \"Kevin\", \"ismarried\" : true}");

        Assert.assertTrue(4 == testMap.size());
        Assert.assertTrue(testMap.containsValue("Kevin"));
        Assert.assertTrue(testMap.containsKey("car"));
        Assert.assertTrue(testMap.containsValue(5.0));
        Assert.assertTrue(testMap.containsValue(true));

        /*for(Map.Entry<String, Object> entry: testMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/
    }
}
