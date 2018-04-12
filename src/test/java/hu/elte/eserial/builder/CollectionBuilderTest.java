package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CollectionBuilderTest {

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        new CollectionBuilder(Date.class).build(Arrays.asList("2"));
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidValue_ThrowsEserialBuilderMismatchException() {
        Set<String> set = new HashSet<>();
        set.add("2");

        new CollectionBuilder(List.class).build(set);
    }

    @Test
    public void build_GivenListNullValue_ReturnsNullValue() {
        assertNull(new CollectionBuilder(List.class).build(null));
    }

    @Test
    public void build_GivenSortedSet_ReturnsTreeSet() {
        Collection builtCollection = new CollectionBuilder(SortedSet.class).build(Arrays.asList("2"));

        assertEquals(TreeSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenSet_ReturnsHashSet() {
        Collection builtCollection = new CollectionBuilder(Set.class).build(Arrays.asList("2"));

        assertEquals(HashSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenTransferQueue_ReturnsLinkedTransferQueue() {
        Collection builtCollection = new CollectionBuilder(TransferQueue.class).build(Arrays.asList("2"));

        assertEquals(LinkedTransferQueue.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenBlockingQueue_ReturnsPriorityBlockingQueue() {
        Collection builtCollection = new CollectionBuilder(BlockingQueue.class).build(Arrays.asList("2"));

        assertEquals(PriorityBlockingQueue.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenBlockingDeque_ReturnsLinkedBlockingDeque() {
        Collection builtCollection = new CollectionBuilder(BlockingDeque.class).build(Arrays.asList("2"));

        assertEquals(LinkedBlockingDeque.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenQueue_ReturnsArrayDeque() {
        Collection builtCollection = new CollectionBuilder(Queue.class).build(Arrays.asList("2"));

        assertEquals(ArrayDeque.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenList_ReturnsArrayList() {
        List builtCollection = new CollectionBuilder(List.class).build(Arrays.asList("2"));

        assertEquals(ArrayList.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenConcurrentSkipListSet_ReturnsConcurrentSkipListSet() {
        Collection builtCollection = new CollectionBuilder(ConcurrentSkipListSet.class).build(Arrays.asList("2"));

        assertEquals(ConcurrentSkipListSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenLinkedTransferQueue_ReturnsLinkedTransferQueue() {
        Collection builtCollection = new CollectionBuilder(LinkedTransferQueue.class).build(Arrays.asList("2"));

        assertEquals(LinkedTransferQueue.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenHashSet_ReturnsHashSet() {
        Collection builtCollection = new CollectionBuilder(HashSet.class).build(Arrays.asList("2"));

        assertEquals(HashSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenTreeSet_ReturnsTreeSet() {
        Collection builtCollection = new CollectionBuilder(TreeSet.class).build(Arrays.asList("2"));

        assertEquals(TreeSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenLinkedList_ReturnsLinkedList() {
        Collection builtCollection = new CollectionBuilder(LinkedList.class).build(Arrays.asList("2"));

        assertEquals(LinkedList.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenArrayDeque_ReturnsArrayDeque() {
        Collection builtCollection = new CollectionBuilder(ArrayDeque.class).build(Arrays.asList("2"));

        assertEquals(ArrayDeque.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenListWithWildCard_ReturnsArrayList() {
        List<?> builtList = new CollectionBuilder(List.class).build(Arrays.asList("2"));

        assertEquals(ArrayList.class, builtList.getClass());
        assertEquals(1, builtList.size());
    }
}
