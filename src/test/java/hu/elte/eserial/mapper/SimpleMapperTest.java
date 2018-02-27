package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleMapperTest {

    private SimpleMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new SimpleMapper();
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        mapper.map(mapper);
    }

    @Test
    public void map_GivenNull_ReturnsNull() {
        assertEquals(null, mapper.map(null));
    }

    @Test
    public void map_GivenPrimitiveTypes_ReturnsItself() {
        assertEquals(0, mapper.map(0));
        assertEquals(0.0f, mapper.map(0.0f));
        assertEquals(true, mapper.map(true));
        assertEquals('0', mapper.map('0'));
    }

    @Test
    public void map_GivenWrapperTypes_ReturnsItself() {
        assertEquals(new Integer(0), mapper.map(new Integer(0)));
        assertEquals(new Float(0.0f), mapper.map(new Float(0.0f)));
        assertEquals(new Boolean(true), mapper.map(new Boolean(true)));
        assertEquals(new Character('0'), mapper.map(new Character('0')));
    }

    @Test
    public void map_GivenString_ReturnsItself() {
        assertEquals("Eserial", mapper.map("Eserial"));
    }
}