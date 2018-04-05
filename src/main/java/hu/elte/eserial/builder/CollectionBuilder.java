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
     * Constructs an {@link CollectionBuilder} and sets the {@code clazz} in it.
     *
     * @param type the {@link Collection} to be used in the {@link AbstractBuilder#build} method
     */
    CollectionBuilder(Type type) {
        super(type);
    }

    /**
     * @param value {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a collection of the given class initialized from {@code value}, which is an ArrayList
     */
    @Override
    public <T> T build(Object value) {
        if (value == null) {
            return null;
        }

        Class clazz = TypeUtils.convertTypeToClass(type);
        Type typeArg = TypeUtils.getTypeArgument(type, 0);

        if (!TypeUtils.isCollection(clazz) || !TypeUtils.isList(value.getClass())) {
            throw new EserialBuilderMismatchException(Collection.class.getSimpleName(), clazz.getName());
        }

        try {
            List<Object> list = (List) value;

            Class typeArgClass = TypeUtils.convertTypeToClass(typeArg);

            if (typeArg != null && TypeUtils.isCompound(typeArgClass)) {
                AbstractBuilder abstractBuilder = BuilderFactory.create(typeArg);
                List compoundListItemList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Object innerObject = abstractBuilder.build(list.get(i));
                    compoundListItemList.add(innerObject);
                }
                list = compoundListItemList;
            }

            Collection collectionObject;

            if (clazz.isInterface()) {
                if (TypeUtils.isSortedSet(clazz)) {
                    collectionObject = new TreeSet<>();
                } else if (TypeUtils.isSet(clazz)) {
                    collectionObject = new HashSet<>();
                } else if (TypeUtils.isTransferQueue(clazz)) {
                    collectionObject = new LinkedTransferQueue();
                } else if (TypeUtils.isBlockingDeque(clazz)) {
                    collectionObject = new LinkedBlockingDeque();
                } else if (TypeUtils.isBlockingQueue(clazz)) {
                    collectionObject = new PriorityBlockingQueue();
                } else if (TypeUtils.isQueue(clazz)) {
                    collectionObject = new ArrayDeque<>();
                } else {
                    collectionObject = new ArrayList<>();
                }
            } else {
                 collectionObject = (Collection) clazz.newInstance();
            }

            collectionObject.addAll(list);

            return (T) collectionObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize collection", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize collection", e);
        }
    }
}
