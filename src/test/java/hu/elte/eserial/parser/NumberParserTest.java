package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberParserTest {
    @Test
    public void parser_GivenAnPairWithInteger_ReturnEntry() {
        Number number = new NumberParser().parser("2");

        assertEquals(new Long(2), number);
        assertEquals(java.lang.Long.class, number.getClass());
    }

    @Test
    public void parser_GivenAnPairWithDouble_ReturnEntry() {
        Number number = new NumberParser().parser("2.0");

        assertEquals(2.0, number);
        assertEquals(java.lang.Double.class, number.getClass());
    }
}
