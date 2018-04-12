package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BooleanParserTest {
    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new BooleanParser(null).parser();
    }

    @Test
    public void parser_GivenAPStringWhichRepresentATrueBoolean_ReturnTrueInEntry() {
        Boolean bool = new BooleanParser("true").parser();

        assertEquals(bool, true);
        assertEquals(bool.getClass(), Boolean.class);
    }

    @Test
    public void parser_GivenAStringWhichRepresentAFalseBoolean_ReturnFalseInEntry() {
        Boolean bool = new BooleanParser("false").parser();

        assertEquals(bool.getClass(), Boolean.class);
        assertEquals(bool, false);
    }
}
