package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Array;
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
     * @param {@code that} an array
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that, RecursionChecker recursionChecker) {
        if (!TypeUtils.isArray(that.getClass())) {
            throw new EserialMapperMismatchException(Array.class.getSimpleName(), that.getClass().getSimpleName());
        }

        Object[] array = (Object[]) that;
        List<Object> list = Arrays.asList(array);

        return list
                .stream()
                .map(element -> MapperFactory.create(element.getClass()).map(element, recursionChecker))
                .collect(Collectors.toList());
    }
}