package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberParserTest {
    @Test
    public void parser_GivenAnInteger_ReturnEntry() {
        ParserEntry<String, Object> entry = new NumberParser().parser("\"test\" : 2");

        assertEquals("test", entry.getKey());
        assertEquals(new Long(2), entry.getValue());
        assertEquals(java.lang.Long.class, entry.getValue().getClass());
    }

    @Test
    public void parser_GivenAnDouble_ReturnEntry() {
        ParserEntry<String, Object> entry = new NumberParser().parser("\"test\" : 2.0");

        assertEquals("test", entry.getKey());
        assertEquals(2.0, entry.getValue());
        assertEquals(java.lang.Double.class, entry.getValue().getClass());
    }
}
