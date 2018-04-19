package hu.elte.eserial.builder;

import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.*;

/**
 * Provides an adequate {@link java.util.Collection} implementation for a given {@link Class}.
 */
public class CollectionFactory {

    /**
     * Prevents the accidental instantiation of this factory class.
     */
    private CollectionFactory() {}

    /**
     * Creates a type-specific {@link Collection} instance.
     *
     * @param collectionClass an arbitrary {@link Class}
     * @return a {@link Collection} instance
     * @throws InstantiationException if could not instantiate the non-interface {@link Collection}
     * @throws IllegalAccessException if could not instantiate the non-interface {@link Collection}
     */
    public static Collection create(Class collectionClass) throws InstantiationException, IllegalAccessException {
        Collection collectionObject;

        if (collectionClass.isInterface()) {
            if (TypeUtils.isAssignableFrom(collectionClass, SortedSet.class)) {
                collectionObject = new TreeSet<>();
            } else if (TypeUtils.isAssignableFrom(collectionClass, Set.class)) {
                collectionObject = new HashSet<>();
            } else if (TypeUtils.isAssignableFrom(collectionClass, TransferQueue.class)) {
                collectionObject = new LinkedTransferQueue();
            } else if (TypeUtils.isAssignableFrom(collectionClass, BlockingDeque.class)) {
                collectionObject = new LinkedBlockingDeque();
            } else if (TypeUtils.isAssignableFrom(collectionClass, BlockingQueue.class)) {
                collectionObject = new PriorityBlockingQueue();
            } else if (TypeUtils.isAssignableFrom(collectionClass, Queue.class)) {
                collectionObject = new ArrayDeque<>();
            } else {
                collectionObject = new ArrayList<>();
            }
        } else {
            collectionObject = (Collection) collectionClass.newInstance();
        }

        return collectionObject;
    }
}
