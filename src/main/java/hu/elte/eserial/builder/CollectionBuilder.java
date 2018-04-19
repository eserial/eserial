package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.*;

/**
 * Builds Collection objects (e.g List).
 */
public class CollectionBuilder extends AbstractBuilder {

    /**
     * Constructs an {@link CollectionBuilder} and sets the {@code collectionType} in it.
     *
     * @param collectionType the {@link Collection} to be used in the {@link AbstractBuilder#build} method
     */
    CollectionBuilder(Type collectionType) {
        super(collectionType);
    }

    /**
     * @param initializationObject {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a {@link Collection} of the given {@link Class} initialized from {@code initializationObject}
     * , which is a {@link LinkedList}
     */
    @Override
    public <T> T build(Object initializationObject) {
        if (initializationObject == null) {
            return null;
        }

        Class typeClass = TypeUtils.convertTypeToClass(type);
        Type argumentType = TypeUtils.getTypeArgument(type, 0);

        if (!TypeUtils.isAssignableFrom(typeClass, Collection.class)) {
            throw new EserialBuilderMismatchException(Collection.class.getSimpleName(), typeClass.getName());
        }

        if (!TypeUtils.isAssignableFrom(initializationObject.getClass(), List.class)) {
            throw new EserialInputTypeMismatchException(List.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            List<Object> initializationList = (List) initializationObject;
            List<Object> builtList = new ArrayList<>();

            for (Object object : initializationList) {
                if (argumentType == null) {
                    builtList.add(object);
                } else {
                    AbstractBuilder abstractBuilder = BuilderFactory.create(argumentType);

                    Object builtElement = abstractBuilder.build(object);
                    builtList.add(builtElement);
                }
            }

            Collection collectionObject;

            if (typeClass.isInterface()) {
                if (TypeUtils.isAssignableFrom(typeClass, SortedSet.class)) {
                    collectionObject = new TreeSet<>();
                } else if (TypeUtils.isAssignableFrom(typeClass, Set.class)) {
                    collectionObject = new HashSet<>();
                } else if (TypeUtils.isAssignableFrom(typeClass, TransferQueue.class)) {
                    collectionObject = new LinkedTransferQueue();
                } else if (TypeUtils.isAssignableFrom(typeClass, BlockingDeque.class)) {
                    collectionObject = new LinkedBlockingDeque();
                } else if (TypeUtils.isAssignableFrom(typeClass, BlockingQueue.class)) {
                    collectionObject = new PriorityBlockingQueue();
                } else if (TypeUtils.isAssignableFrom(typeClass, Queue.class)) {
                    collectionObject = new ArrayDeque<>();
                } else {
                    collectionObject = new ArrayList<>();
                }
            } else {
                 collectionObject = (Collection) typeClass.newInstance();
            }

            collectionObject.addAll(builtList);

            return (T) collectionObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize collection", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize collection", e);
        }
    }
}
