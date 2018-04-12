package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class StringParserTest {
    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new StringParser(null).parser();
    }

    @Test
    public void parser_GivenASimplePairWithTwoString_ReturnAEntry() {
       String value = new StringParser("test").parser();

       assertEquals(value, "test");
    }
}
