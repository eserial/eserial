package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullParserTest {
    @Test
    public void nullParser_GivenASimpleStringWhichRepresentANullObject_ReturnEntry() {
        ParserEntry<String, Object> entry = new NullParser().parser("{\"test\" : null}");

        assertEquals("test", entry.getKey());
        assertEquals(null, entry.getValue());
    }
}
