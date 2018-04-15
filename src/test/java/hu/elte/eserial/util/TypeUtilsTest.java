package hu.elte.eserial.util;

import hu.elte.eserial.builder.CompoundBuilderTest;
import hu.elte.eserial.model.Setter;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.*;

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

    @Test
    public void isDecimal_GivenDecimal_ReturnsTrue() {
        assertTrue(TypeUtils.isDecimal(double.class));
        assertTrue(TypeUtils.isDecimal(Double.class));
        assertTrue(TypeUtils.isDecimal(float.class));
        assertTrue(TypeUtils.isDecimal(Float.class));
    }

    @Test
    public void isDecimal_GivenNonDecimal_ReturnsFalse() {
        assertFalse(TypeUtils.isDecimal(Integer.class));
    }

    @Test
    public void isLong_GivenLong_ReturnsTrue() {
        assertTrue(TypeUtils.isLong(Long.class));
        assertTrue(TypeUtils.isLong(long.class));
    }

    @Test
    public void isLong_GivenNonLong_ReturnsFalse() {
        assertFalse(TypeUtils.isLong(Integer.class));
    }

    @Test
    public void isCollection_GivenCollection_ReturnsTrue() {
        assertTrue(TypeUtils.isCollection(Set.class));
        assertTrue(TypeUtils.isCollection(List.class));
        assertTrue(TypeUtils.isCollection(Queue.class));
    }

    @Test
    public void isCollection_GivenNonCollection_ReturnsFalse() {
        assertFalse(TypeUtils.isCollection(Integer.class));
        assertFalse(TypeUtils.isCollection(Map.class));
        assertFalse(TypeUtils.isCollection(Date.class));
    }

    @Test
    public void isSortedSet_GivenSortedSet_ReturnsTrue() {
        assertTrue(TypeUtils.isSortedSet(SortedSet.class));
        assertTrue(TypeUtils.isSortedSet(TreeSet.class));
    }

    @Test
    public void isSortedSet_GivenNonSortedSet_ReturnsFalse() {
        assertFalse(TypeUtils.isSortedSet(HashSet.class));
    }

    @Test
    public void isSet_GivenSet_ReturnsTrue() {
        assertTrue(TypeUtils.isSet(Set.class));
        assertTrue(TypeUtils.isSet(HashSet.class));
    }

    @Test
    public void isSet_GivenNonSet_ReturnsFalse() {
        assertFalse(TypeUtils.isSet(Queue.class));
    }

    @Test
    public void isQueue_GivenQueue_ReturnsTrue() {
        assertTrue(TypeUtils.isQueue(Queue.class));
        assertTrue(TypeUtils.isQueue(ArrayDeque.class));
    }

    @Test
    public void isQueue_GivenNonQueue_ReturnsFalse() {
        assertFalse(TypeUtils.isQueue(Set.class));
    }

    @Test
    public void isConcurrentNavigableMap_GivenNavigableMap_ReturnsTrue() {
        assertTrue(TypeUtils.isConcurrentNavigableMap(ConcurrentNavigableMap.class));
        assertTrue(TypeUtils.isConcurrentNavigableMap(ConcurrentSkipListMap.class));
    }

    @Test
    public void isConcurrentNavigableMap_GivenNonConcurrentNavigableMap_ReturnsFalse() {
        assertFalse(TypeUtils.isConcurrentNavigableMap(HashMap.class));
    }

    @Test
    public void isConcurrentMap_GivenConcurrentMap_ReturnsTrue() {
        assertTrue(TypeUtils.isConcurrentMap(ConcurrentMap.class));
        assertTrue(TypeUtils.isConcurrentMap(ConcurrentSkipListMap.class));
    }

    @Test
    public void isConcurrentMap_GivenNonConcurrentMap_ReturnsFalse() {
        assertFalse(TypeUtils.isConcurrentMap(Set.class));
    }

    @Test
    public void isSortedMap_GivenSortedMap_ReturnsTrue() {
        assertTrue(TypeUtils.isSortedMap(SortedMap.class));
        assertTrue(TypeUtils.isSortedMap(TreeMap.class));
    }

    @Test
    public void isSortedMap_GivenNonSortedMap_ReturnsFalse() {
        assertFalse(TypeUtils.isSortedMap(Set.class));
    }

    @Test
    public void isBlockingQueue_GivenBlockingQueue_ReturnsTrue() {
        assertTrue(TypeUtils.isBlockingQueue(LinkedBlockingQueue.class));
        assertTrue(TypeUtils.isBlockingQueue(BlockingQueue.class));
    }

    @Test
    public void isBlockingQueue_GivenNonBlockingQueue_ReturnsFalse() {
        assertFalse(TypeUtils.isBlockingQueue(Set.class));
    }

    @Test
    public void isBlockingDeque_GivenBlockingDeque_ReturnsTrue() {
        assertTrue(TypeUtils.isBlockingDeque(BlockingDeque.class));
        assertTrue(TypeUtils.isBlockingDeque(LinkedBlockingDeque.class));
    }

    @Test
    public void isBlockingDeque_GivenNonBlockingDeque_ReturnsFalse() {
        assertFalse(TypeUtils.isBlockingDeque(Set.class));
    }

    @Test
    public void isTransferQueue_GivenTransferQueue_ReturnsTrue() {
        assertTrue(TypeUtils.isTransferQueue(TransferQueue.class));
        assertTrue(TypeUtils.isTransferQueue(LinkedTransferQueue.class));
    }

    @Test
    public void isTransferQueue_GivenNonTransferQueue_ReturnsFalse() {
        assertFalse(TypeUtils.isTransferQueue(Set.class));
    }

    @Test
    public void convertTypeToClass_GivenType_ReturnsItsClass() {
        Type type = List.class;

        assertEquals(List.class, TypeUtils.convertTypeToClass(type));
    }

    @Test
    public void convertTypeToClass_GivenParameterizedType_ReturnsItsClass() {
        List<Integer> list = new ArrayList<>();

        assertEquals(ArrayList.class, TypeUtils.convertTypeToClass(list.getClass()));
    }

    @Test
    public void getTypeArgument_GivenNonParameterizedType_ReturnsNull() {
        assertNull(TypeUtils.getTypeArgument(Integer.class.getGenericSuperclass(), 0));
    }

    @Test
    public void getTypeArgument_GivenParameterizedType_ReturnsTypeArguments() throws NoSuchMethodException {
            Method method = CompoundBuilderTest.CompoundTestClassTwo.class.getDeclaredMethod("setList", List.class);
            Setter setter = new Setter(new CompoundBuilderTest.CompoundTestClassTwo(), method);

            Type type = setter.getTypeOfSetterParameter();

            assertEquals(Integer.class, TypeUtils.getTypeArgument(type, 0));
    }

    @Test
    public void getTypeArgument_GivenTypeWithNoGenericParameter_ReturnsNull() throws NoSuchMethodException {
        Method method = CompoundBuilderTest.CollectionDataMember.class.getDeclaredMethod("setLinkedListDataMember"
                , LinkedList.class);
        Setter setter = new Setter(new CompoundBuilderTest.CollectionDataMember(), method);

        Type type = setter.getTypeOfSetterParameter();

        assertNull(TypeUtils.getTypeArgument(type, 0));
    }
}