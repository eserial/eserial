package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumMapperTest {

    private EnumMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new EnumMapper();
    }

    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        mapper.map(null, null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        mapper.map(0, null);
    }

    enum TestEnum {
        A, B, C
    }

    @Test
    public void map_GivenAnEnum_ReturnsItsOrdinalValue() {
        assertEquals(0, mapper.map(TestEnum.A, null));
        assertEquals(1, mapper.map(TestEnum.B, null));
        assertEquals(2, mapper.map(TestEnum.C, null));
    }
}