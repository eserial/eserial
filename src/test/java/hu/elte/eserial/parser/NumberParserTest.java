package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberParserTest {
    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new NumberParser(null).parse();
    }

    @Test
    public void parser_GivenAnJsonWhichStartWithAnInteger_ReturnLong() {
        Number number = new NumberParser("2 , \"key1\" : ").parse();

        assertEquals(new Long(2), number);
        assertEquals(java.lang.Long.class, number.getClass());
    }

    @Test
    public void parser_GivenAnJsonWhichStartWithADouble_ReturnDouble() {
        Number number = new NumberParser("2.0, \"key1\" : ").parse();

        assertEquals(2.0, number);
        assertEquals(java.lang.Double.class, number.getClass());
    }
}
