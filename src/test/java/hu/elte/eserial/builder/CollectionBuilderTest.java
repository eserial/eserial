package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TransferQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CollectionBuilderTest {

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        new CollectionBuilder(Date.class).build(Arrays.asList("2"), null);
    }

    @Test(expected = EserialInputTypeMismatchException.class)
    public void build_GivenInvalidValue_ThrowsEserialInputTypeMismatchException() {
        Set<String> set = new HashSet<>();
        set.add("2");

        new CollectionBuilder(List.class).build(set, null);
    }

    @Test
    public void build_GivenListNullValue_ReturnsNullValue() {
        assertNull(new CollectionBuilder(List.class).build(null, null));
    }

    @Test
    public void build_GivenSortedSet_ReturnsTreeSet() {
        Collection builtCollection = new CollectionBuilder(SortedSet.class).build(Arrays.asList("2"), null);

        assertEquals(TreeSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenSet_ReturnsHashSet() {
        Collection builtCollection = new CollectionBuilder(Set.class).build(Arrays.asList("2"), null);

        assertEquals(HashSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenTransferQueue_ReturnsLinkedTransferQueue() {
        Collection builtCollection = new CollectionBuilder(TransferQueue.class).build(Arrays.asList("2"), null);

        assertEquals(LinkedTransferQueue.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenBlockingQueue_ReturnsPriorityBlockingQueue() {
        Collection builtCollection = new CollectionBuilder(BlockingQueue.class).build(Arrays.asList("2"), null);

        assertEquals(PriorityBlockingQueue.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenBlockingDeque_ReturnsLinkedBlockingDeque() {
        Collection builtCollection = new CollectionBuilder(BlockingDeque.class).build(Arrays.asList("2"), null);

        assertEquals(LinkedBlockingDeque.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenQueue_ReturnsArrayDeque() {
        Collection builtCollection = new CollectionBuilder(Queue.class).build(Arrays.asList("2"), null);

        assertEquals(ArrayDeque.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenList_ReturnsArrayList() {
        List builtCollection = new CollectionBuilder(List.class).build(Arrays.asList("2"), null);

        assertEquals(ArrayList.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenConcurrentSkipListSet_ReturnsConcurrentSkipListSet() {
        Collection builtCollection = new CollectionBuilder(ConcurrentSkipListSet.class).build(Arrays.asList("2"), null);

        assertEquals(ConcurrentSkipListSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenLinkedTransferQueue_ReturnsLinkedTransferQueue() {
        Collection builtCollection = new CollectionBuilder(LinkedTransferQueue.class).build(Arrays.asList("2"), null);

        assertEquals(LinkedTransferQueue.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenHashSet_ReturnsHashSet() {
        Collection builtCollection = new CollectionBuilder(HashSet.class).build(Arrays.asList("2"), null);

        assertEquals(HashSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenTreeSet_ReturnsTreeSet() {
        Collection builtCollection = new CollectionBuilder(TreeSet.class).build(Arrays.asList("2"), null);

        assertEquals(TreeSet.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenLinkedList_ReturnsLinkedList() {
        Collection builtCollection = new CollectionBuilder(LinkedList.class).build(Arrays.asList("2"), null);

        assertEquals(LinkedList.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenArrayDeque_ReturnsArrayDeque() {
        Collection builtCollection = new CollectionBuilder(ArrayDeque.class).build(Arrays.asList("2"), null);

        assertEquals(ArrayDeque.class, builtCollection.getClass());
        assertEquals(1, builtCollection.size());
    }

    @Test
    public void build_GivenListWithWildCard_ReturnsArrayList() {
        List<?> builtList = new CollectionBuilder(List.class).build(Arrays.asList("2"), null);

        assertEquals(ArrayList.class, builtList.getClass());
        assertEquals(1, builtList.size());
    }

    public static class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ListOfUser {
        private List<User> users;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }

    @Test(expected = EserialInputTypeMismatchException.class)
    public void build_GivenListWithInvalidValue_ThrowsEserialInputTypeMismatchException() throws NoSuchMethodException {
        Method method = ListOfUser.class.getDeclaredMethod("setUsers", List.class);
        Type type = method.getGenericParameterTypes()[0];

        new CollectionBuilder(type).build(Arrays.asList("2"), null);
    }
}
