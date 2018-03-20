package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.ParameterizedType;
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
     * @return a collection of the given class and initialized with the list parameter
     */
    @Override
    public <T> T build(Object value) {
        if (value == null) {
            return null;
        }

        Class clazz;
        Type typeArg;

        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            clazz = (Class) pType.getRawType();
            typeArg = pType.getActualTypeArguments()[0];
        } else {
            clazz = (Class) type;
            typeArg = null;
        }

        if (!TypeUtils.isCollection(clazz) || (!TypeUtils.isList(value.getClass()))) {
            throw new EserialBuilderMismatchException(Collection.class.getSimpleName(), clazz.getName());
        }

        try {
            List<Object> list = (List) value;

            Class typeArgClass = (Class) typeArg;
            if (typeArg != null && TypeUtils.isCompound(typeArgClass)) {
                for (int i = 0; i < list.size(); i++) {
                    AbstractBuilder abstractBuilder = BuilderFactory.create(typeArg);
                    Object innerObject = abstractBuilder.build(list.get(i));
                    list.set(i, innerObject);
                }
            }

            if (clazz.isInterface()) {
                if (TypeUtils.isSortedSet(clazz)) {
                    return (T) new TreeSet<>(list);
                } else if (TypeUtils.isSet(clazz)) {
                    return (T) new HashSet<>(list);
                } else if (TypeUtils.isTransferQueue(clazz)) {
                    return (T) new LinkedTransferQueue(list);
                } else if (TypeUtils.isBlockingDeque(clazz)) {
                    return (T) new LinkedBlockingDeque(list);
                } else if (TypeUtils.isBlockingQueue(clazz)) {
                    return (T) new PriorityBlockingQueue(list);
                } else if (TypeUtils.isQueue(clazz)) {
                    return (T) new ArrayDeque<>(list);
                } else {
                    return (T) new ArrayList<>(list);
                }
            }

            Collection collectionObject = (Collection) clazz.newInstance();
            collectionObject.addAll(list);

            return (T) collectionObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException(e.getMessage());
        } catch (InstantiationException e) {
            throw new EserialInstantiationException(e.getMessage());
        }
    }
}
