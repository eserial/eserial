package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleMapperTest {

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        new SimpleMapper(new SimpleMapperTest()).map(null);
    }

    @Test
    public void map_GivenNull_ReturnsNull() {
        assertEquals(null, new SimpleMapper(null).map(null));
    }

    @Test
    public void map_GivenPrimitiveTypes_ReturnsItself() {
        assertEquals(0, new SimpleMapper(0).map(null));
        assertEquals(0.0f, new SimpleMapper(0.0f).map(null));
        assertEquals(true, new SimpleMapper(true).map(null));
        assertEquals('0', new SimpleMapper('0').map(null));
    }

    @Test
    public void map_GivenString_ReturnsItself() {
        assertEquals("Eserial", new SimpleMapper("Eserial").map(null));
    }
}