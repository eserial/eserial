package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.model.EserialContext;
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
     * @param context {@inheritDoc}
     * @return mapped representation of the contained {@link Collection}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object map(EserialContext context) {
        if (!TypeUtils.isAssignableFrom(this.object.getClass(), Collection.class)) {
            throw new EserialMapperMismatchException(Collection.class.getSimpleName(),
                    this.object.getClass().getSimpleName());
        }

        Collection collection = (Collection) this.object;

        return collection
                .stream()
                .map(element -> MapperFactory.create(element).map(context))
                .collect(Collectors.toList());
    }
}
