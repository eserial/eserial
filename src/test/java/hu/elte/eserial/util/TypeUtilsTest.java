package hu.elte.eserial.util;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TypeUtilsTest {

    @Test
    public void isPrimitive_GivenPrimitives_ReturnsTrue() {
        assertTrue(TypeUtils.isPrimitive(byte.class));
        assertTrue(TypeUtils.isPrimitive(short.class));
        assertTrue(TypeUtils.isPrimitive(int.class));
        assertTrue(TypeUtils.isPrimitive(long.class));
        assertTrue(TypeUtils.isPrimitive(float.class));
        assertTrue(TypeUtils.isPrimitive(double.class));
        assertTrue(TypeUtils.isPrimitive(boolean.class));
        assertTrue(TypeUtils.isPrimitive(char.class));
    }

    @Test
    public void isPrimitive_GivenNonPrimitives_ReturnsFalse() {
        assertFalse(TypeUtils.isPrimitive(void.class));
        assertFalse(TypeUtils.isPrimitive(Integer.class));
        assertFalse(TypeUtils.isPrimitive(String.class));
    }

    @Test
    public void isWrapper_GivenWrappers_ReturnsTrue() {
        assertTrue(TypeUtils.isWrapper(Double.class));
        assertTrue(TypeUtils.isWrapper(Float.class));
        assertTrue(TypeUtils.isWrapper(Long.class));
        assertTrue(TypeUtils.isWrapper(Integer.class));
        assertTrue(TypeUtils.isWrapper(Short.class));
        assertTrue(TypeUtils.isWrapper(Character.class));
        assertTrue(TypeUtils.isWrapper(Byte.class));
        assertTrue(TypeUtils.isWrapper(Boolean.class));
    }

    @Test
    public void isWrapper_GivenNonWrappers_ReturnsFalse() {
        assertFalse(TypeUtils.isWrapper(int.class));
        assertFalse(TypeUtils.isWrapper(String.class));
    }

    @Test
    public void isString_GivenString_ReturnsTrue() {
        assertTrue(TypeUtils.isString(String.class));
    }

    @Test
    public void isString_GivenChar_ReturnsFalse() {
        assertFalse(TypeUtils.isString(char.class));
    }

    @Test
    public void isCompound_GivenCompounds_ReturnsTrue() {
        assertTrue(TypeUtils.isCompound(TypeUtils.class));
        assertTrue(TypeUtils.isCompound(TypeUtilsTest.class));
    }

    @Test
    public void isCompound_GivenNonCompounds_ReturnsFalse() {
        assertFalse(TypeUtils.isCompound(int.class));
        assertFalse(TypeUtils.isCompound(Integer.class));
        assertFalse(TypeUtils.isCompound(String.class));
    }

    @Test
    public void isNumber_GivenNumbers_ReturnsTrue() {
        assertTrue(TypeUtils.isNumber(short.class));
        assertTrue(TypeUtils.isNumber(int.class));
        assertTrue(TypeUtils.isNumber(long.class));
        assertTrue(TypeUtils.isNumber(float.class));
        assertTrue(TypeUtils.isNumber(double.class));
        assertTrue(TypeUtils.isNumber(Number.class));
    }

    @Test
    public void isNumber_GivenNonNumbers_ReturnsFalse() {
        assertFalse(TypeUtils.isNumber(char.class));
        assertFalse(TypeUtils.isNumber(Character.class));
        assertFalse(TypeUtils.isNumber(boolean.class));
        assertFalse(TypeUtils.isNumber(Boolean.class));
    }

    @Test
    public void isMap_GivenMaps_ReturnsTrue() {
        assertTrue(TypeUtils.isMap(Map.class));
        assertTrue(TypeUtils.isMap(HashMap.class));
    }

    @Test
    public void isMap_GivenNonMap_ReturnsFalse() {
        assertFalse(TypeUtils.isMap(Integer.class));
    }

    @Test
    public void isArray_GivenArray_ReturnsTrue() {
        assertTrue(TypeUtils.isArray(Integer[].class));
        assertTrue(TypeUtils.isArray(int[].class));
    }

    @Test
    public void isArray_GivenNonArray_ReturnsFalse() {
        assertFalse(TypeUtils.isArray(Integer.class));
    }

    public enum TestEnum {}

    @Test
    public void isEnum_GivenEnum_ReturnsTrue() {
        assertTrue(TypeUtils.isEnum(TestEnum.class));
    }

    @Test
    public void isEnum_GivenNonEnum_ReturnsFalse() {
        assertFalse(TypeUtils.isEnum(Integer.class));
    }

    @Test
    public void isDate_GivenDate_ReturnsTrue() {
        assertTrue(TypeUtils.isDate(Date.class));
    }

    @Test
    public void isDate_GivenNonDate_ReturnsFalse() {
        assertFalse(TypeUtils.isEnum(Integer.class));
    }

    @Test
    public void isList_GivenList_ReturnsTrue() {
        assertTrue(TypeUtils.isList(List.class));
    }

    @Test
    public void isList_GivenNonList_ReturnsFalse() {
        assertFalse(TypeUtils.isList(Map.class));
    }

    @Test
    public void isBoolean_GivenBoolean_ReturnsTrue() {
        assertTrue(TypeUtils.isBoolean(Boolean.class));
        assertTrue(TypeUtils.isBoolean(boolean.class));
    }

    @Test
    public void isBoolean_GivenNonBoolean_ReturnsFalse() {
        assertFalse(TypeUtils.isBoolean(Integer.class));
    }

    @Test
    public void isCharacter_GivenCharacter_ReturnsTrue() {
        assertTrue(TypeUtils.isCharacter(Character.class));
        assertTrue(TypeUtils.isCharacter(char.class));
    }

    @Test
    public void isCharacter_GivenNonCharacter_ReturnsFalse() {
        assertFalse(TypeUtils.isCharacter(Integer.class));
    }
}