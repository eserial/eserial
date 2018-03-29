package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BooleanParserTest {
    @Test
    public void parser_GivenAPairWhichRepresentATrueBoolean_ReturnTrueInEntry() {
        Boolean bool = new BooleanParser().parser("true");

        assertEquals(bool, true);
    }

    @Test
    public void parser_GivenAPairWhichRepresentAFalseBoolean_ReturnFalseInEntry() {
        Boolean bool = new BooleanParser().parser("false");

        assertEquals(bool, false);
    }
}
