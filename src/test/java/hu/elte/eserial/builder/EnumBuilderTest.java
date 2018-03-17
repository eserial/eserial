package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumBuilderTest  {

    enum TestEnum {
        A, B, C
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        new EnumBuilder(Integer.class).build(2L);
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidValue_ThrowsEserialBuilderMismatchException() {
        new EnumBuilder(TestEnum.class).build("2");
    }

    @Test
    public void build_GivenOrdinalValue_ReturnsEnumValue() {
        EnumBuilder enumBuilder = new EnumBuilder(TestEnum.class);

        assertEquals(TestEnum.A, enumBuilder.build(0L));
        assertEquals(TestEnum.B, enumBuilder.build(1L));
        assertEquals(TestEnum.C, enumBuilder.build(2L));
    }
}
