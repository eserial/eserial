package hu.elte.eserial.mapper;

import hu.elte.eserial.annotation.Enumerated;
import hu.elte.eserial.annotation.enumeration.EnumeratedFormat;
import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.model.EserialContext;
import hu.elte.eserial.model.EserialElement;
import hu.elte.eserial.model.Getter;
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

    private EserialContext createContext(String methodName) throws NoSuchMethodException {
        Getter getter = new Getter(new EnumTest(), EnumTest.class.getDeclaredMethod(methodName));
        return EserialContext.forMapperElement(EserialElement.fromAccessor(getter), null);
    }

    @Test
    public void map_GivenAnEnumWithEnumeratedFormatName_ReturnsItsName() throws NoSuchMethodException {
        EnumTest enumTest = new EnumTest();
        assertEquals("A", new EnumMapper(enumTest.getByName())
                .map(createContext("getByName")));
    }

    @Test
    public void map_GivenAnEnumWithEnumeratedFormatOrdinal_ReturnsItsOrdinalValue() throws NoSuchMethodException {
        EnumTest enumTest = new EnumTest();
        assertEquals(1, new EnumMapper(enumTest.getByOrdinal())
                .map(createContext("getByOrdinal")));
    }

    @Test
    public void map_GivenAnEnumWithNoAnnotations_ReturnsItsOrdinalValue() throws NoSuchMethodException {
        EnumTest enumTest = new EnumTest();
        assertEquals(2, new EnumMapper(enumTest.getNoAnnotation())
                .map(createContext("getNoAnnotation")));
    }
}
