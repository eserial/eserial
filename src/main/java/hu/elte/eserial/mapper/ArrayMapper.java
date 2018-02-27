package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps array-like objects (e.g Integer[]).
 */
public class ArrayMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the array {@code that}.
     *
     * @param that an array
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that) {
        if (!TypeUtils.isArray(that.getClass())) {
            throw new EserialMapperMismatchException(that.getClass());
        }

        Object[] array = (Object[]) that;
        List<Object> list = Arrays.asList(array);

        return list
                .stream()
                .map(element -> MapperFactory.create(element.getClass()).map(element))
                .collect(Collectors.toList());
    }
}