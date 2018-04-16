package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BooleanParserTest {
    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new BooleanParser(null).parse();
    }

    @Test
    public void parser_GivenAPStringWhichRepresentATrueBoolean_ReturnTrueInEntry() {
        Boolean bool = new BooleanParser("true").parse();

        assertEquals(true, bool);
    }

    @Test
    public void parser_GivenAStringWhichRepresentAFalseBoolean_ReturnFalseInEntry() {
        Boolean bool = new BooleanParser("false").parse();

        assertEquals(false, bool);
    }
}
