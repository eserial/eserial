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
    public void isAssignableFrom_GivenMaps_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(Map.class, Map.class));
        assertTrue(TypeUtils.isAssignableFrom(HashMap.class, Map.class));
    }

    @Test
    public void isAssignableFrom_GivenNonMap_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Integer.class, Map.class));
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
    public void isAssignableFrom_GivenList_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(List.class, List.class));
    }

    @Test
    public void isAssignableFrom_GivenNonList_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Map.class, List.class));
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
    public void isAssignableFrom_GivenCollectionTargetClassAndCollectionFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(Set.class, Collection.class));
        assertTrue(TypeUtils.isAssignableFrom(List.class, Collection.class));
        assertTrue(TypeUtils.isAssignableFrom(Queue.class, Collection.class));
    }

    @Test
    public void isAssignableFrom_GivenCollectionTargetClassAndNonCollectionFromClasses_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Integer.class, Collection.class));
        assertFalse(TypeUtils.isAssignableFrom(Map.class, Collection.class));
        assertFalse(TypeUtils.isAssignableFrom(Date.class, Collection.class));
    }

    @Test
    public void isAssignableFrom_GivenSortedSetTargetClassAndSortedSetFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(SortedSet.class, SortedSet.class));
        assertTrue(TypeUtils.isAssignableFrom(TreeSet.class, SortedSet.class));
    }

    @Test
    public void isAssignableFrom_GivenSortedSetTargetClassAndHashSetFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(HashSet.class, SortedSet.class));
    }

    @Test
    public void isAssignableFrom_GivenSetTargetClassAndSetFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(Set.class, Set.class));
        assertTrue(TypeUtils.isAssignableFrom(HashSet.class, Set.class));
    }

    @Test
    public void isAssignableFrom_GivenSetTargetClassAndQueueFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Queue.class, Set.class));
    }

    @Test
    public void isAssignableFrom_GivenQueueTargetClassAndQueueFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(Queue.class, Queue.class));
        assertTrue(TypeUtils.isAssignableFrom(ArrayDeque.class, ArrayDeque.class));
    }

    @Test
    public void isAssignableFrom_GivenQueueTargetClassAndSetFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Set.class, Queue.class));
    }

    @Test
    public void isAssignableFrom_GivenConcurrentNavigableMapTargetClassAndConcurrentNavigableMapFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(ConcurrentNavigableMap.class, ConcurrentNavigableMap.class));
        assertTrue(TypeUtils.isAssignableFrom(ConcurrentSkipListMap.class, ConcurrentNavigableMap.class));
    }

    @Test
    public void isAssignableFrom_GivenConcurrentNavigableMapTargetClassAndHashMapFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(HashMap.class, ConcurrentNavigableMap.class));
    }

    @Test
    public void isAssignableFrom_GivenConcurrentMapTargetClassAndConcurrentMapFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(ConcurrentMap.class, ConcurrentMap.class));
        assertTrue(TypeUtils.isAssignableFrom(ConcurrentSkipListMap.class, ConcurrentSkipListMap.class));
    }

    @Test
    public void isAssignableFrom_GivenConcurrentMapTargetClassAndSetFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Set.class, ConcurrentMap.class));
    }

    @Test
    public void isAssignableFrom_GivenSortedMapTargetClassAndSortedMapFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(SortedMap.class, SortedMap.class));
        assertTrue(TypeUtils.isAssignableFrom(TreeMap.class, SortedMap.class));
    }

    @Test
    public void isAssignableFrom_GivenSortedMapTargetClassAndSetFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Set.class, SortedMap.class));
    }

    @Test
    public void isAssignableFrom_GivenBlockingQueueTargetClassAndBlockingQueueFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(LinkedBlockingQueue.class, BlockingQueue.class));
        assertTrue(TypeUtils.isAssignableFrom(BlockingQueue.class, BlockingQueue.class));
    }

    @Test
    public void isAssignableFrom_GivenBlockingQueueTargetClassAndSetFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Set.class, BlockingQueue.class));
    }

    @Test
    public void isAssignableFrom_GivenBlockingDequeTargetClassAndBlockingQueueFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(BlockingDeque.class, BlockingDeque.class));
        assertTrue(TypeUtils.isAssignableFrom(LinkedBlockingDeque.class, BlockingDeque.class));
    }

    @Test
    public void isAssignableFrom_GivenBlockingDequeTargetClassAndSetFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Set.class, BlockingDeque.class));
    }

    @Test
    public void isAssignableFrom_GivenTransferQueueTargetClassAndTransferQueueFromClasses_ReturnsTrue() {
        assertTrue(TypeUtils.isAssignableFrom(TransferQueue.class, TransferQueue.class));
        assertTrue(TypeUtils.isAssignableFrom(LinkedTransferQueue.class, TransferQueue.class));
    }

    @Test
    public void isAssignableFrom_GivenTransferQueueTargetClassAndSetFromClass_ReturnsFalse() {
        assertFalse(TypeUtils.isAssignableFrom(Set.class, TransferQueue.class));
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