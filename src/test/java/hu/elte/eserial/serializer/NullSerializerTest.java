package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullSerializerTest {

    @Test(expected = EserialSerializerMismatchException.class)
    public void serialize_GivenInvalidType_ThrowsEserialSerializerMismatchException() {
        new NullSerializer(true).serialize();
    }

    @Test
    public void serialize_GivenNull_ReturnsNullAsString() {
        assertEquals("null", new NullSerializer(null).serialize());
    }
}