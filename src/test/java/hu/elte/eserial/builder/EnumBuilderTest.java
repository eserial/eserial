package hu.elte.eserial.builder;

import hu.elte.eserial.annotation.Enumerated;
import hu.elte.eserial.annotation.enumeration.EnumeratedFormat;
import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.exception.EserialInvalidEnumException;
import hu.elte.eserial.model.EserialContext;
import hu.elte.eserial.model.EserialElement;
import hu.elte.eserial.model.Setter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EnumBuilderTest  {

    enum TestEnum {
        A, B, C
    }

    public class EnumTest {
        @Enumerated(EnumeratedFormat.NAME)
        private TestEnum byName = TestEnum.A;
        @Enumerated(EnumeratedFormat.ORDINAL)
        private TestEnum byOrdinal = TestEnum.B;
        private TestEnum noAnnotation = TestEnum.C;

        public void setByName(TestEnum byName) {
            this.byName = byName;
        }

        public void setByOrdinal(TestEnum byOrdinal) {
            this.byOrdinal = byOrdinal;
        }

        public void setNoAnnotation(TestEnum noAnnotation) {
            this.noAnnotation = noAnnotation;
        }
    }

    private EserialContext createContext(String methodName) throws NoSuchMethodException {
        Setter setter = new Setter(new EnumTest(), EnumTest.class.getDeclaredMethod(methodName, TestEnum.class));
        return EserialContext.forBuilderElement(EserialElement.fromAccessor(setter));
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() throws NoSuchMethodException {
        new EnumBuilder(Integer.class).build(2L, createContext("setNoAnnotation"));
    }

    @Test(expected = EserialInputTypeMismatchException.class)
    public void build_GivenDoubleValue_ThrowsEserialInputTypeMismatchException() throws NoSuchMethodException {
        new EnumBuilder(TestEnum.class).build(3.14, createContext("setNoAnnotation"));
    }

    @Test(expected = EserialInvalidEnumException.class)
    public void build_GivenInvalidOrdinal_ThrowsEserialInvalidEnumException() throws NoSuchMethodException {
        new EnumBuilder(TestEnum.class).build(3L, createContext("setNoAnnotation"));
    }

    @Test(expected = EserialInvalidEnumException.class)
    public void build_GivenInvalidNameToEnumeratedFormatName_ThrowsEserialInvalidEnumException() throws NoSuchMethodException {
        new EnumBuilder(TestEnum.class).build("Invalid_Name", createContext("setByName"));
    }

    @Test
    public void build_GivenStringValueWithNoAnnotation_ReturnsEnumValue() throws NoSuchMethodException {
        EnumBuilder enumBuilder = new EnumBuilder(TestEnum.class);

        assertEquals(TestEnum.C, enumBuilder.build("2", createContext("setNoAnnotation")));
    }

    @Test
    public void build_GivenEnumNullValue_ReturnsNullValue() throws NoSuchMethodException {
        assertNull(new EnumBuilder(TestEnum.class)
                .build(null, createContext("setNoAnnotation")));
    }

    @Test
    public void build_GivenOrdinalValue_ReturnsEnumValue() throws NoSuchMethodException {
        EnumBuilder enumBuilder = new EnumBuilder(TestEnum.class);

        assertEquals(TestEnum.A, enumBuilder.build(0L, createContext("setNoAnnotation")));
        assertEquals(TestEnum.B, enumBuilder.build(1L, createContext("setNoAnnotation")));
        assertEquals(TestEnum.C, enumBuilder.build(2L, createContext("setNoAnnotation")));
    }

    @Test
    public void build_GivenEnumWithEnumeratedFormatName_BuildsByName() throws NoSuchMethodException {
        EnumBuilder enumBuilder = new EnumBuilder(TestEnum.class);

        assertEquals(TestEnum.A, enumBuilder.build("A", createContext("setByName")));
        assertEquals(TestEnum.B, enumBuilder.build("B", createContext("setByName")));
        assertEquals(TestEnum.C, enumBuilder.build("C", createContext("setByName")));
    }

    @Test
    public void build_GivenEnumWithEnumeratedFormatOrdinal_BuildsByOrdinal() throws NoSuchMethodException {
        EnumBuilder enumBuilder = new EnumBuilder(TestEnum.class);

        assertEquals(TestEnum.A, enumBuilder.build(0L, createContext("setByOrdinal")));
        assertEquals(TestEnum.B, enumBuilder.build(1L, createContext("setByOrdinal")));
        assertEquals(TestEnum.C, enumBuilder.build(2L, createContext("setByOrdinal")));
    }
}
