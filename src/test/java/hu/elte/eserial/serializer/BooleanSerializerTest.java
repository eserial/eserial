package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BooleanSerializerTest {

    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new BooleanSerializer(null).serialize();
    }

    @Test(expected = EserialSerializerMismatchException.class)
    public void serialize_GivenInvalidType_ThrowsEserialSerializerMismatchException() {
        new BooleanSerializer(1).serialize();
    }

    @Test
    public void serialize_GivenTrue_ReturnsTrueAsString() {
        assertEquals("true", new BooleanSerializer(true).serialize());
    }

    @Test
    public void serialize_GivenFalse_ReturnsFalseAsString() {
        assertEquals("false", new BooleanSerializer(false).serialize());
    }
}