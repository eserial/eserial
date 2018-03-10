package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.model.EserialContext;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps array-like objects (e.g Integer[]).
 */
public class ArrayMapper extends AbstractMapper {

    /**
     * Constructs an {@link ArrayMapper} and sets the {@code object} in it.
     *
     * @param object the {@code array} to be used in the {@link AbstractMapper#map} method
     */
    ArrayMapper(Object object) {
        super(object);
    }

    /**
     * @param context {@inheritDoc}
     * @return mapped representation of the contained array
     */
    @Override
    public Object map(EserialContext context) {
        if (!TypeUtils.isArray(this.object.getClass())) {
            throw new EserialMapperMismatchException(Array.class.getSimpleName(),
                    this.object.getClass().getSimpleName());
        }

        Object[] array = (Object[]) this.object;
        List<Object> list = Arrays.asList(array);

        return list
                .stream()
                .map(element -> MapperFactory.create(element).map(context))
                .collect(Collectors.toList());
    }
}
