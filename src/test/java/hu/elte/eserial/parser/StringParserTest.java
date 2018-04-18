package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class StringParserTest {
    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new StringParser(null).parse();
    }

    @Test
    public void parser_GivenASimpleString_ReturnString() {
       String value = new StringParser("\"test\"").parse();

       assertEquals("test", value);
    }

    @Test
    public void parser_GivenASimpleStringWhichHasAnEscapedCharacter_ReturnString() {
        String value = new StringParser("\"te\\nst\"").parse();

        assertEquals("te\nst", value);
    }
}
