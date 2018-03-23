package hu.elte.eserial.parser;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class StringParserTest {
    @Test
    public void parser_GivenASimplePairWithTwoString_ReturnAEntry() {
       ParserEntry<String, Object> entry = new StringParser().parser(new Pair("test", "test"));

       assertEquals(entry.getKey(), "test");
       assertEquals(entry.getValue(), "test");
    }
}
