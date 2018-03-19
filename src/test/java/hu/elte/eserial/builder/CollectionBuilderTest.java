package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class CollectionBuilderTest {

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        new CollectionBuilder(Date.class).build(list);
    }

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidValue_ThrowsEserialBuilderMismatchException() {
        Set<String> set = new HashSet<>();
        set.add("2");

        new CollectionBuilder(List.class).build(set);
    }

    @Test
    public void build_GivenSortedSet_ReturnsTreeSet() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(SortedSet.class).build(list);

        assertEquals(TreeSet.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenSet_ReturnsHashSet() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(Set.class).build(list);

        assertEquals(HashSet.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenTransferQueue_ReturnsLinkedTransferQueue() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(TransferQueue.class).build(list);

        assertEquals(LinkedTransferQueue.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenBlockingQueue_ReturnsPriorityBlockingQueue() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(BlockingQueue.class).build(list);

        assertEquals(PriorityBlockingQueue.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenBlockingDeque_ReturnsLinkedBlockingDeque() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(BlockingDeque.class).build(list);

        assertEquals(LinkedBlockingDeque.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenQueue_ReturnsArrayDeque() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(Queue.class).build(list);

        assertEquals(ArrayDeque.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenList_ReturnsArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(List.class).build(list);

        assertEquals(ArrayList.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenConcurrentSkipListSet_ReturnsConcurrentSkipListSet() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(ConcurrentSkipListSet.class).build(list);

        assertEquals(ConcurrentSkipListSet.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenLinkedTransferQueue_ReturnsLinkedTransferQueue() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(LinkedTransferQueue.class).build(list);

        assertEquals(LinkedTransferQueue.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenHashSet_ReturnsHashSet() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(HashSet.class).build(list);

        assertEquals(HashSet.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenTreeSet_ReturnsTreeSet() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(TreeSet.class).build(list);

        assertEquals(TreeSet.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenLinkedList_ReturnsLinkedList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(LinkedList.class).build(list);

        assertEquals(LinkedList.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }

    @Test
    public void build_GivenArrayDeque_ReturnsArrayDeque() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2");

        Collection buildedCollection = new CollectionBuilder(ArrayDeque.class).build(list);

        assertEquals(ArrayDeque.class, buildedCollection.getClass());
        assertEquals(1, buildedCollection.size());
    }
}
