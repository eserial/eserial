package hu.elte.eserial.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
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
    }

    @Test
    public void parser_GivenACompoundObject() {
        Map<String, Object> testMap = new ObjectParser().parser("{\"car\": 2, \"house\" : 5, \"name\": \"Kevin\", \"ismarried\" : true, \"object\" : {\"region\" : \"west\"}}");

        Assert.assertTrue(5 == testMap.size());

        for (Map.Entry<String, Object> entry : testMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
