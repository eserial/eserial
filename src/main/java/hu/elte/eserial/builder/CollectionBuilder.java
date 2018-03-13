package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.TypeUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class CollectionBuilder {

    public static Collection build(Object value, Class type) throws Exception {
        if (!TypeUtils.isCollection(type) || !TypeUtils.isList(value.getClass())) {
            throw new EserialException("Type mismatch");
        }

        List<Object> list = (List) value;

        if (type.isInterface()) {
            if (TypeUtils.isSortedSet(type)) {
                return new TreeSet<>(list);
            } else if (TypeUtils.isSet(type)) {
                return new HashSet<>(list);
            } else if (TypeUtils.isTransferQueue(type)) {
                return new LinkedTransferQueue(list);
            } else if (TypeUtils.isBlockingDeque(type)) {
                return new LinkedBlockingDeque(list);
            } else if (TypeUtils.isBlockingQueue(type)) {
                return new PriorityBlockingQueue(list);
            } else if (TypeUtils.isQueue(type)) {
                return new ArrayDeque<>(list);
            } else {
                return new ArrayList<>(list);
            }
        }

        Collection collectionObject = (Collection) type.newInstance();
        collectionObject.addAll(list);

        return collectionObject;
    }
}
