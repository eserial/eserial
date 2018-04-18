package hu.elte.eserial.parser;

import hu.elte.eserial.exception.EserialInvalidJsonException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParserFactoryTest {

    @Test(expected = NullPointerException.class)
    public void create_GivenNull_ThrowsNullPointerException() {
        ParserFactory.create(null);
    }

    @Test(expected = EserialInvalidJsonException.class)
    public void create_GivenInvalidString_ThrowsEserialInvalidJsonException() {
        assertTrue(ParserFactory.create("test") instanceof NullParser);
    }

    @Test
    public void create_GivenStringWhichRepresentANumber_ReturnsNumberParser() {
        assertTrue(ParserFactory.create("1") instanceof NumberParser);
        assertTrue(ParserFactory.create("1.0") instanceof NumberParser);
        assertTrue(ParserFactory.create("-1.0") instanceof NumberParser);
    }

    @Test
    public void create_GivenStringWhichRepresentABoolean_ReturnsNumberParser() {
        assertTrue(ParserFactory.create("true") instanceof BooleanParser);
        assertTrue(ParserFactory.create("false") instanceof BooleanParser);
    }

    @Test
    public void create_GivenStringWhichRepresentAString_ReturnsNumberParser() {
        assertTrue(ParserFactory.create("\"eserial\"") instanceof StringParser);
    }

    @Test
    public void create_GivenStringWhichRepresentAList_ReturnsNumberParser() {
        assertTrue(ParserFactory.create("[1,2,3]") instanceof CollectionParser);
    }

    @Test
    public void create_GivenStringWhichRepresentAnObject_ReturnsNumberParser() {
        assertTrue(ParserFactory.create("{\"test\" : 2}") instanceof ObjectParser);
    }

    @Test
    public void create_GivenStringWhichRepresentANullObject_ReturnsNumberParser() {
        assertTrue(ParserFactory.create("null") instanceof NullParser);
    }
}
