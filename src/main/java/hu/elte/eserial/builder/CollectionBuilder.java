package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
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

        Class classOfCollectionType = TypeUtils.convertTypeToClass(type);
        Type typeOfCollectionTypeArgument = TypeUtils.getTypeArgument(type, 0);

        if (!TypeUtils.isAssignableFrom(classOfCollectionType, Collection.class)) {
            throw new EserialBuilderMismatchException(Collection.class.getSimpleName(), classOfCollectionType.getName());
        }

        if (!TypeUtils.isAssignableFrom(initializationObject.getClass(), List.class)) {
            throw new EserialBuilderMismatchException(List.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            List<Object> initializationList = (List) initializationObject;
            List<Object> builtList = new ArrayList<>();

            for (Object object : initializationList) {
                if (typeOfCollectionTypeArgument == null) {
                    builtList.add(object);
                } else {
                    AbstractBuilder abstractBuilder = BuilderFactory.create(typeOfCollectionTypeArgument);

                    Object builtElement = abstractBuilder.build(object);
                    builtList.add(builtElement);
                }
            }

            Collection collectionObject;

            if (classOfCollectionType.isInterface()) {
                if (TypeUtils.isAssignableFrom(classOfCollectionType, SortedSet.class)) {
                    collectionObject = new TreeSet<>();
                } else if (TypeUtils.isAssignableFrom(classOfCollectionType, Set.class)) {
                    collectionObject = new HashSet<>();
                } else if (TypeUtils.isAssignableFrom(classOfCollectionType, TransferQueue.class)) {
                    collectionObject = new LinkedTransferQueue();
                } else if (TypeUtils.isAssignableFrom(classOfCollectionType, BlockingDeque.class)) {
                    collectionObject = new LinkedBlockingDeque();
                } else if (TypeUtils.isAssignableFrom(classOfCollectionType, BlockingQueue.class)) {
                    collectionObject = new PriorityBlockingQueue();
                } else if (TypeUtils.isAssignableFrom(classOfCollectionType, Queue.class)) {
                    collectionObject = new ArrayDeque<>();
                } else {
                    collectionObject = new ArrayList<>();
                }
            } else {
                 collectionObject = (Collection) classOfCollectionType.newInstance();
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
