package hu.elte.eserial.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;


public class StringParserTest {
    @Test
    public void parser_GivenASimpleJsonString_ReturnAMap() {
       Map<String, Object> testMap = new StringParser().parser("{\"car\":\"Audi\"}");

       Assert.assertTrue(testMap.containsKey("car"));
       Assert.assertTrue(testMap.containsValue("Audi"));
       Assert.assertTrue(1 == testMap.size());
    }
}
