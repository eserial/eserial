package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberSerializerTest {

    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new NumberSerializer(null).serialize();
    }

    @Test(expected = EserialSerializerMismatchException.class)
    public void serialize_GivenInvalidType_ThrowsEserialSerializerMismatchException() {
        new NumberSerializer(true).serialize();
    }

    @Test
    public void serialize_GivenANumber_ReturnsItsStringRepresentation() {
        assertEquals("1", new NumberSerializer((long) 1).serialize());
        assertEquals("1", new NumberSerializer(new Long(1)).serialize());
        assertEquals("1", new NumberSerializer(1).serialize());
        assertEquals("1.01", new NumberSerializer(1.01f).serialize());
        assertEquals("1.01", new NumberSerializer(1.01).serialize());
    }
}