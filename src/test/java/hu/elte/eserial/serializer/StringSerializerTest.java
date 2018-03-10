package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringSerializerTest {

    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new StringSerializer(null).serialize();
    }

    @Test(expected = EserialSerializerMismatchException.class)
    public void serialize_GivenInvalidType_ThrowsEserialSerializerMismatchException() {
        new StringSerializer(true).serialize();
    }

    @Test
    public void serialize_GivenEmptyString_ReturnsEmptyEnquotedString() {
        assertEquals("\"\"", new StringSerializer("").serialize());
    }

    @Test
    public void serialize_GivenAString_ReturnsEnquotedString() {
        assertEquals("\"eserial\"", new StringSerializer("eserial").serialize());
    }

    @Test
    public void serialize_GivenAStringWithSpecialCharacter_ReturnsEnquotedEscapedString() {
        assertEquals("\"eserial\\\"\"", new StringSerializer("eserial\"").serialize());
    }
}