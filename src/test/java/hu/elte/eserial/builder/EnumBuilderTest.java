package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.exception.EserialInvalidEnumOrdinalException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EnumBuilderTest  {

    enum TestEnum {
        A, B, C
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        new EnumBuilder(Integer.class).build(2L);
    }

    @Test(expected = EserialInputTypeMismatchException.class)
    public void build_GivenDoubleValue_ThrowsEserialInputTypeMismatchException() {
        new EnumBuilder(TestEnum.class).build(3.14);
    }

    @Test(expected = EserialInvalidEnumOrdinalException.class)
    public void build_GivenInvalidOrdinal_ThrowsEserialInputTypeMismatchException() {
        new EnumBuilder(TestEnum.class).build(3L);
    }

    @Test
    public void build_GivenStringValue_ReturnsEnumValue() {
        EnumBuilder enumBuilder = new EnumBuilder(TestEnum.class);

        assertEquals(TestEnum.C, enumBuilder.build("2"));
    }

    @Test
    public void build_GivenEnumNullValue_ReturnsNullValue() {
        assertNull(new EnumBuilder(TestEnum.class).build(null));
    }

    @Test
    public void build_GivenOrdinalValue_ReturnsEnumValue() {
        EnumBuilder enumBuilder = new EnumBuilder(TestEnum.class);

        assertEquals(TestEnum.A, enumBuilder.build(0L));
        assertEquals(TestEnum.B, enumBuilder.build(1L));
        assertEquals(TestEnum.C, enumBuilder.build(2L));
    }
}
