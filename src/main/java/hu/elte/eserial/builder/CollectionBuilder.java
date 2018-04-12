package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;

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
     * @return a collection of the given class initialized from {@code initializationObject}, which is an ArrayList
     */
    @Override
    public <T> T build(Object initializationObject) {
        if (initializationObject == null) {
            return null;
        }

        Class classOfCollectionType = TypeUtils.convertTypeToClass(type);
        Type typeOfCollectionTypeArgument = TypeUtils.getTypeArgument(type, 0);

        if (!TypeUtils.isCollection(classOfCollectionType)) {
            throw new EserialBuilderMismatchException(Collection.class.getSimpleName(), classOfCollectionType.getName());
        }

        if (!TypeUtils.isList(initializationObject.getClass())) {
            throw new EserialBuilderMismatchException(List.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            List<Object> initializationList = (List) initializationObject;

            Class classOfTypeArgumentType = TypeUtils.convertTypeToClass(typeOfCollectionTypeArgument);

            if (typeOfCollectionTypeArgument != null && TypeUtils.isCompound(classOfTypeArgumentType)) {
                AbstractBuilder abstractBuilder = BuilderFactory.create(typeOfCollectionTypeArgument);
                List compoundListItemList = new ArrayList();
                for (int i = 0; i < initializationList.size(); i++) {
                    Object innerObject = abstractBuilder.build(initializationList.get(i));
                    compoundListItemList.add(innerObject);
                }
                initializationList = compoundListItemList;
            }

            Collection collectionObject;

            if (classOfCollectionType.isInterface()) {
                if (TypeUtils.isSortedSet(classOfCollectionType)) {
                    collectionObject = new TreeSet<>();
                } else if (TypeUtils.isSet(classOfCollectionType)) {
                    collectionObject = new HashSet<>();
                } else if (TypeUtils.isTransferQueue(classOfCollectionType)) {
                    collectionObject = new LinkedTransferQueue();
                } else if (TypeUtils.isBlockingDeque(classOfCollectionType)) {
                    collectionObject = new LinkedBlockingDeque();
                } else if (TypeUtils.isBlockingQueue(classOfCollectionType)) {
                    collectionObject = new PriorityBlockingQueue();
                } else if (TypeUtils.isQueue(classOfCollectionType)) {
                    collectionObject = new ArrayDeque<>();
                } else {
                    collectionObject = new ArrayList<>();
                }
            } else {
                 collectionObject = (Collection) classOfCollectionType.newInstance();
            }

            collectionObject.addAll(initializationList);

            return (T) collectionObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize collection", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize collection", e);
        }
    }
}
