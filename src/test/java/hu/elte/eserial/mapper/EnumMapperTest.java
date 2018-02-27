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
    public void map_withNull() {
        mapper.map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_withInvalidType() {
        mapper.map(0);
    }

    enum TestEnum {
        A, B, C
    }

    @Test
    public void map_withEnum() {
        assertEquals(0, mapper.map(TestEnum.A));
        assertEquals(1, mapper.map(TestEnum.B));
        assertEquals(2, mapper.map(TestEnum.C));
    }
}