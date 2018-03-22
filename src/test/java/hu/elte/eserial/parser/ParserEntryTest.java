package hu.elte.eserial.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserEntryTest {
    @Test
    public void parserEntry_GivenTwoValidArgument_ReturnEntry() {
        ParserEntry parserEntry = new ParserEntry("test", "value");

        assertEquals("test", parserEntry.getKey());
        assertEquals("value", parserEntry.getValue());
    }

    @Test
    public void setValue_SetNewValidValue_ReturnEntry() {
        ParserEntry parserEntry = new ParserEntry("test", "value");

        assertEquals("value", parserEntry.setValue("test"));
        assertEquals("test", parserEntry.getValue());
    }
}
