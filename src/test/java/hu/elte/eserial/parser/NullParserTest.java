package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullParserTest {
    @Test
    public void parser_GivenAPairWhichRepresentANullObject_ReturnEntry() {
        Object nullObject = new NullParser("null").parser();

        assertEquals(null, nullObject);
    }
}
