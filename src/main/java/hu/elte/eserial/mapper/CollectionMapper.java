package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Maps Collection-like objects (e.g ArrayList, HashSet).
 */
public class CollectionMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the Collection {@code that}.
     *
     * @param {@code that} a collection
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of {@code that}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object map(Object that, RecursionChecker recursionChecker) {
        if (!TypeUtils.isCollection(that.getClass())) {
            throw new EserialMapperMismatchException(Collection.class.getSimpleName(), that.getClass().getSimpleName());
        }

        Collection collection = (Collection) that;

        return collection
                .stream()
                .map(element -> MapperFactory.create(element.getClass()).map(element, recursionChecker))
                .collect(Collectors.toList());
    }
}
