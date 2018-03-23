package hu.elte.eserial.parser;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BooleanParserTest {
    @Test
    public void parser_GivenAPairWhichRepresentATrueBoolean_ReturnTrueInEntry() {
        ParserEntry<String, Object> entry = new BooleanParser().parser(new Pair("test", "true"));

        assertEquals(entry.getKey(),"test");
        assertEquals(entry.getValue(), true);
    }

    @Test
    public void parser_GivenAPairWhichRepresentAFalseBoolean_ReturnFalseInEntry() {
        ParserEntry<String, Object> entry = new BooleanParser().parser(new Pair("test", "false"));

        assertEquals(entry.getKey(),"test");
        assertEquals(entry.getValue(), false);
    }
}
