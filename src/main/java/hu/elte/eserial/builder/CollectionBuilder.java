package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.util.TypeUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class CollectionBuilder extends AbstractBuilder {

    CollectionBuilder(Class type) {
        super(type);
    }

    @Override
    public <T> T build(Object value) {
        if (!TypeUtils.isCollection(type) || !TypeUtils.isList(value.getClass())) {
            throw new EserialBuilderMismatchException(Collection.class.getSimpleName(), type.getName());
        }

        try {
            List<Object> list = (List) value;

            if (type.isInterface()) {
                if (TypeUtils.isSortedSet(type)) {
                    return (T) new TreeSet<>(list);
                } else if (TypeUtils.isSet(type)) {
                    return (T) new HashSet<>(list);
                } else if (TypeUtils.isTransferQueue(type)) {
                    return (T) new LinkedTransferQueue(list);
                } else if (TypeUtils.isBlockingDeque(type)) {
                    return (T) new LinkedBlockingDeque(list);
                } else if (TypeUtils.isBlockingQueue(type)) {
                    return (T) new PriorityBlockingQueue(list);
                } else if (TypeUtils.isQueue(type)) {
                    return (T) new ArrayDeque<>(list);
                } else {
                    return (T) new ArrayList<>(list);
                }
            }

            Collection collectionObject = (Collection) type.newInstance();
            collectionObject.addAll(list);

            return (T) collectionObject;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException(e.getMessage());
        } catch (InstantiationException e) {
            throw new EserialInstantiationException(e.getMessage());
        }
    }
}
