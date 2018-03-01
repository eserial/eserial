package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumMapperTest {

    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        new EnumMapper(null).map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        new EnumMapper(0).map(null);
    }

    enum TestEnum {
        A, B, C
    }

    @Test
    public void map_GivenAnEnum_ReturnsItsOrdinalValue() {
        assertEquals(0, new EnumMapper(TestEnum.A).map(null));
        assertEquals(1, new EnumMapper(TestEnum.B).map(null));
        assertEquals(2, new EnumMapper(TestEnum.C).map(null));
    }
}