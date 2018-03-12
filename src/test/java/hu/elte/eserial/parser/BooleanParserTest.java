package hu.elte.eserial.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class BooleanParserTest {
    @Test
    public void parser_GiveASimpleString() {
        Map<String, Object> testMap = new BooleanParser().parser("{\"hasNext\": true}");

        Assert.assertTrue(testMap.containsKey("hasNext"));
        Assert.assertTrue(testMap.containsValue(true));
        Assert.assertTrue(1 == testMap.size());
    }
}
