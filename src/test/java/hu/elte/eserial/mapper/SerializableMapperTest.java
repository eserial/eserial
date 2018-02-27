package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SerializableMapperTest {

    private SerializableMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new SerializableMapper();
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_withInvalidType() {
        mapper.map(mapper);
    }

    @Test
    public void map_withNull() {
        assertEquals(null, mapper.map(null));
    }

    @Test
    public void map_withPrimitiveTypes() {
        assertEquals(0, mapper.map(0));
        assertEquals(0.0f, mapper.map(0.0f));
        assertEquals(true, mapper.map(true));
        assertEquals('0', mapper.map('0'));
    }

    @Test
    public void map_withWrapperTypes() {
        assertEquals(new Integer(0), mapper.map(new Integer(0)));
        assertEquals(new Float(0.0f), mapper.map(new Float(0.0f)));
        assertEquals(new Boolean(true), mapper.map(new Boolean(true)));
        assertEquals(new Character('0'), mapper.map(new Character('0')));
    }

    @Test
    public void map_withString() {
        assertEquals("Eserial", mapper.map("Eserial"));
    }
}