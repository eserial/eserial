package hu.elte.eserial.mapper;

import hu.elte.eserial.annotation.Enumerated;
import hu.elte.eserial.annotation.enumeration.EnumeratedFormat;
import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.model.EserialContext;
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

    class EnumTest {
        @Enumerated(EnumeratedFormat.NAME)
        private TestEnum byName = TestEnum.A;
        @Enumerated(EnumeratedFormat.ORDINAL)
        private TestEnum byOrdinal = TestEnum.B;
        private TestEnum noAnnotation = TestEnum.C;

        public TestEnum getByName() {
            return byName;
        }

        public TestEnum getByOrdinal() {
            return byOrdinal;
        }

        public TestEnum getNoAnnotation() {
            return noAnnotation;
        }
    }

    @Test
    public void map_GivenAnEnumWithEnumeratedFormatName_ReturnsItsName() {
        EnumTest enumTest = new EnumTest();
        assertEquals("A", new EnumMapper(enumTest.getByName())
                .map(EserialContext.forElement(enumTest, "getByName", null)));
    }

    @Test
    public void map_GivenAnEnumWithEnumeratedFormatOrdinal_ReturnsItsOrdinalValue() {
        EnumTest enumTest = new EnumTest();
        assertEquals(1, new EnumMapper(enumTest.getByOrdinal())
                .map(EserialContext.forElement(enumTest, "getByOrdinal", null)));
    }

    @Test
    public void map_GivenAnEnumWithNoAnnotations_ReturnsItsOrdinalValue() {
        EnumTest enumTest = new EnumTest();
        assertEquals(2, new EnumMapper(enumTest.getNoAnnotation())
                .map(EserialContext.forElement(enumTest, "getNoAnnotation", null)));
    }
}
