package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.util.TypeUtils;

import java.util.*;

public class CollectionBuilder {

    public static Collection build(Object value, Class type) {
        if (!TypeUtils.isCollection(type) || !TypeUtils.isList(value.getClass())) {
            throw new EserialException("Type mismatch");
        }

        List<Object> list = (List) value;

        if (TypeUtils.isSortedSet(type)) {
            return new TreeSet<>(list);
        } else if (TypeUtils.isSet(type)) {
            return new HashSet<>(list);
        } else if (TypeUtils.isQueue(type)) {
            return new ArrayDeque<>(list);
        } else  {
            return new ArrayList<>(list);
        }
    }
}
