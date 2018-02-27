package hu.elte.eserial.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TypeUtilsTest {

    @Test
    public void isPrimitive_withPrimitives() {
        assertFalse(TypeUtils.isPrimitive(byte.class));
        assertTrue(TypeUtils.isPrimitive(short.class));
        assertTrue(TypeUtils.isPrimitive(int.class));
        assertTrue(TypeUtils.isPrimitive(long.class));
        assertTrue(TypeUtils.isPrimitive(float.class));
        assertTrue(TypeUtils.isPrimitive(double.class));
        assertTrue(TypeUtils.isPrimitive(boolean.class));
        assertTrue(TypeUtils.isPrimitive(char.class));
    }

    @Test
    public void isPrimitive_withNonPrimitives() {
        assertFalse(TypeUtils.isPrimitive(void.class));
        assertFalse(TypeUtils.isPrimitive(Integer.class));
        assertFalse(TypeUtils.isPrimitive(String.class));
    }

    @Test
    public void isWrapper_withWrappers() {
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
    public void isWrapper_withNonWrappers() {
        assertFalse(TypeUtils.isWrapper(int.class));
        assertFalse(TypeUtils.isWrapper(String.class));
    }

    @Test
    public void isString_withString() {
        assertTrue(TypeUtils.isString(String.class));
    }

    @Test
    public void isString_withChar() {
        assertFalse(TypeUtils.isString(char.class));
    }

    @Test
    public void isCompound_withCompounds() {
        assertTrue(TypeUtils.isCompound(TypeUtils.class));
        assertTrue(TypeUtils.isCompound(TypeUtilsTest.class));
    }

    @Test
    public void isCompound_withNonCompounds() {
        assertFalse(TypeUtils.isCompound(int.class));
        assertFalse(TypeUtils.isCompound(Integer.class));
        assertFalse(TypeUtils.isCompound(String.class));
    }

    @Test
    public void isMap_withMaps() {
        assertTrue(TypeUtils.isMap(Map.class));
        assertTrue(TypeUtils.isMap(HashMap.class));
    }

    @Test
    public void isMap_withNonMap() {
        assertFalse(TypeUtils.isMap(Integer.class));
    }

    @Test
    public void isArray_withArrays() {
        assertTrue(TypeUtils.isArray(Integer[].class));
        assertTrue(TypeUtils.isArray(int[].class));
    }

    @Test
    public void isArray_withNonArray() {
        assertFalse(TypeUtils.isArray(Integer.class));
    }

    public enum TestEnum {}

    @Test
    public void isEnum_withEnum() {
        assertTrue(TypeUtils.isEnum(TestEnum.class));
    }

    @Test
    public void isEnum_withNonEnum() {
        assertFalse(TypeUtils.isEnum(Integer.class));
    }
}