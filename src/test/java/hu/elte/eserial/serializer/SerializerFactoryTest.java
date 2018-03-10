package hu.elte.eserial.serializer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SerializerFactoryTest {

    @Test
    public void create_GivenNull_ReturnsNullSerializer() {
        assertTrue(SerializerFactory.create(null) instanceof NullSerializer);
    }

    @Test
    public void create_GivenNumberType_ReturnsNumberSerializer() {
        assertTrue(SerializerFactory.create(1) instanceof NumberSerializer);
        assertTrue(SerializerFactory.create(1.0f) instanceof NumberSerializer);
        assertTrue(SerializerFactory.create(1.0) instanceof NumberSerializer);
    }

    @Test
    public void create_GivenBooleanType_ReturnsBooleanSerializer() {
        assertTrue(SerializerFactory.create(true) instanceof BooleanSerializer);
        assertTrue(SerializerFactory.create(false) instanceof BooleanSerializer);
    }

    @Test
    public void create_GivenStringType_ReturnsStringSerializer() {
        assertTrue(SerializerFactory.create("eserial") instanceof StringSerializer);
    }

    @Test
    public void create_GivenCharacterType_ReturnsStringSerializer() {
        assertTrue(SerializerFactory.create('e') instanceof StringSerializer);
    }

    @Test
    public void create_GivenListType_ReturnsArraySerializer() {
        List<Integer> list = new ArrayList<>();
        assertTrue(SerializerFactory.create(list) instanceof ArraySerializer);
    }

    private class TestClass {}

    @Test
    public void create_GivenObjectType_ReturnsObjectSerializer() {
        assertTrue(SerializerFactory.create(new TestClass()) instanceof ObjectSerializer);
    }
}