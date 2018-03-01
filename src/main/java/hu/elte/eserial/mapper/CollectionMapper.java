package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Maps Collection-like objects (e.g ArrayList, HashSet).
 */
public class CollectionMapper extends AbstractMapper {

    /**
     * Constructs a {@link CollectionMapper} and sets the {@code object} in it.
     *
     * @param object the {@link Collection} to be used in the {@link AbstractMapper#map} method
     */
    CollectionMapper(Object object) {
        super(object);
    }

    /**
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of the contained {@link Collection}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object map(RecursionChecker recursionChecker) {
        if (!TypeUtils.isCollection(this.object.getClass())) {
            throw new EserialMapperMismatchException(Collection.class.getSimpleName(),
                    this.object.getClass().getSimpleName());
        }

        Collection collection = (Collection) this.object;

        return collection
                .stream()
                .map(element -> MapperFactory.create(element).map(recursionChecker))
                .collect(Collectors.toList());
    }
}
