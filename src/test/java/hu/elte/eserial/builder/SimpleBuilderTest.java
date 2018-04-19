package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.exception.EserialPrimitiveCanNotBeNullException;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SimpleBuilderTest {

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        new SimpleBuilder(Date.class).build(3L);
    }

    @Test(expected = EserialInputTypeMismatchException.class)
    public void build_GivenInvalidValue_ThrowsEserialInputTypeMismatchException() {
        new SimpleBuilder(Integer.class).build(new Date());
    }

    @Test(expected = EserialPrimitiveCanNotBeNullException.class)
    public void build_GivenPrimitiveTypeNullValue_ThrowsEserialPrimitiveCanNotBeNullException() {
        new SimpleBuilder(int.class).build(null);
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenCharacterTypeStringValue_ThrowsEserialBuilderMismatchException() {
        new SimpleBuilder(Character.class).build("character");
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenBooleanTypeStringValue_ThrowsEserialBuilderMismatchException() {
        new SimpleBuilder(Boolean.class).build("notboolean");
    }

    @Test
    public void build_GivenStringTypeNullValue_ReturnsStringWithNullValue() {
        assertNull(new SimpleBuilder(String.class).build(null));
    }

    @Test
    public void build_GivenIntPrimitive_ReturnsIntValue() {
        assertEquals(new Integer(1), new SimpleBuilder(int.class).build(1L));
    }

    @Test
    public void build_GivenIntWrapper_ReturnsIntValue() {
        assertEquals(new Integer(1), new SimpleBuilder(Integer.class).build(1L));
    }

    @Test
    public void build_GivenShortPrimitive_ReturnsShortValue() {
        assertEquals(new Short((short) 1), new SimpleBuilder(short.class).build(1L));
    }

    @Test
    public void build_GivenShortWrapper_ReturnsShortValue() {
        assertEquals(new Short((short) 1), new SimpleBuilder(Short.class).build(1L));
    }

    @Test
    public void build_GivenBytePrimitive_ReturnsByteValue() {
        assertEquals(new Byte((byte) 1), new SimpleBuilder(byte.class).build(1L));
    }

    @Test
    public void build_GivenByteWrapper_ReturnsByteValue() {
        assertEquals(new Byte((byte) 1), new SimpleBuilder(Byte.class).build(1L));
    }

    @Test
    public void build_GivenLongPrimitive_ReturnsLongValue() {
        assertEquals(new Long(1), new SimpleBuilder(long.class).build(1L));
    }

    @Test
    public void build_GivenLongWrapper_ReturnsLongValue() {
        assertEquals(new Long(1), new SimpleBuilder(Long.class).build(1L));
    }

    @Test
    public void build_GivenFloatPrimitive_ReturnsFloatValue() {
        assertEquals(new Float(1.5), new SimpleBuilder(float.class).build(1.5d));
    }

    @Test
    public void build_GivenFloatWrapper_ReturnsFloatValue() {
        assertEquals(new Float(1.5), new SimpleBuilder(Float.class).build(1.5d));
    }

    @Test
    public void build_GivenDoublePrimitive_ReturnsDoubleValue() {
        assertEquals(new Double(1.5), new SimpleBuilder(double.class).build(1.5d));
    }

    @Test
    public void build_GivenDoubleWrapper_ReturnsDoubleValue() {
        assertEquals(new Double(1.5), new SimpleBuilder(Double.class).build(1.5d));
    }

    @Test
    public void build_GivenCharPrimitive_ReturnsChar() {
        assertEquals(new Character('c'), new SimpleBuilder(char.class).build('c'));
    }

    @Test
    public void build_GivenCharWrapper_ReturnsChar() {
        assertEquals(new Character('c'), new SimpleBuilder(Character.class).build('c'));
    }

    @Test
    public void build_GivenBooleanPrimitive_ReturnsBooleanValue() {
        assertEquals(new Boolean("true"), new SimpleBuilder(boolean.class).build(true));
    }

    @Test
    public void build_GivenBooleanWrapper_ReturnsBooleanValue() {
        assertEquals(new Boolean("true"), new SimpleBuilder(Boolean.class).build(true));
    }

    @Test
    public void build_GivenString_ReturnsStringValue() {
        assertEquals("string", new SimpleBuilder(String.class).build("string"));
    }

    @Test
    public void build_GivenString_ReturnsCharacterValue() {
        assertEquals(new Character('c'), new SimpleBuilder(Character.class).build("c"));
    }

    @Test
    public void build_GivenString_ReturnsBooleanValue() {
        assertEquals(new Boolean("true"), new SimpleBuilder(Boolean.class).build("tRue"));
    }

    @Test
    public void build_GivenString_ReturnsLongValue() {
        assertEquals(new Long(1), new SimpleBuilder(Long.class).build("1"));
    }

    @Test
    public void build_GivenString_ReturnsDecimalValue() {
        assertEquals(new Double(1.5), new SimpleBuilder(Double.class).build("1.5"));
    }
}
