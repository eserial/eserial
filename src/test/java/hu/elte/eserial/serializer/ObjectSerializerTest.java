package hu.elte.eserial.serializer;

import hu.elte.eserial.exception.EserialSerializerMismatchException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ObjectSerializerTest {

    @Test(expected = NullPointerException.class)
    public void serializer_GivenNull_ThrowsNullPointerException() {
        new ObjectSerializer(null).serialize();
    }

    @Test(expected = EserialSerializerMismatchException.class)
    public void serialize_GivenInvalidType_ThrowsEserialSerializerMismatchException() {
        new ObjectSerializer(true).serialize();
    }

    @Test
    public void serialize_givenAnEmptyMap_ReturnsEmptyJsonObject() {
        Map<String, Object> map = new HashMap<>();
        assertEquals("{}", new ObjectSerializer(map).serialize());
    }

    @Test
    public void serialize_givenAMapWithSimpleTypes_ReturnsJsonObjectWithThoseValues() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value");
        map.put("key2", 1234);
        map.put("key3", true);

        assertEquals("{\"key1\":\"value\",\"key2\":1234,\"key3\":true}", new ObjectSerializer(map).serialize());
    }

    @Test
    public void serialize_givenAMapWithCompoundTypes_ReturnsJsonObjectWithThoseValues() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value");
        map.put("key2", 1234);
        map.put("key3", true);
        map.put("key4", new HashMap<>(map));

        assertEquals("{\"key1\":\"value\",\"key2\":1234,\"key3\":true,\"key4\":{\"key1\":\"value\",\"key2\":1234,\"key3\":true}}", new ObjectSerializer(map).serialize());
    }
}