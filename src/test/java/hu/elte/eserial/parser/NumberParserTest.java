package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberParserTest {
    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new NumberParser(null).parser();
    }

    @Test
    public void parser_GivenAnPairWithInteger_ReturnEntry() {
        Number number = new NumberParser("2").parser();

        assertEquals(new Long(2), number);
        assertEquals(java.lang.Long.class, number.getClass());
    }

    @Test
    public void parser_GivenAnPairWithDouble_ReturnEntry() {
        Number number = new NumberParser("2.0").parser();

        assertEquals(2.0, number);
        assertEquals(java.lang.Double.class, number.getClass());
    }

    @Test(expected = NumberFormatException.class)
    public void parser_GivenAnInvalidNumber_ReturnEntry() {
        new NumberParser("test2").parser();
    }
}
