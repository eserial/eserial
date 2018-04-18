package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BooleanParserTest {
    @Test
    public void parser_GivenAPStringWhichRepresentATrueBoolean_ReturnTrue() {
        Boolean bool = new BooleanParser("true").parse();

        assertEquals(true, bool);
    }

    @Test
    public void parser_GivenAStringWhichRepresentAFalseBoolean_ReturnFalse() {
        Boolean bool = new BooleanParser("false").parse();

        assertEquals(false, bool);
    }
}
