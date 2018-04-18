package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullParserTest {
    @Test
    public void parser_GivenAPairWhichRepresentANullObject_ReturnNull() {
        Object nullObject = new NullParser("null").parse();

        assertEquals(null, nullObject);
    }
}
