package hu.elte.eserial.parser;

import javafx.util.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullParserTest {
    @Test
    public void parser_GivenAPairWhichRepresentANullObject_ReturnEntry() {
        ParserEntry<String, Object> entry = new NullParser().parser(new Pair<>("test", "null"));

        assertEquals("test", entry.getKey());
        assertEquals(null, entry.getValue());
    }
}
