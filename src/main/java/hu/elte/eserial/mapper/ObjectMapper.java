package hu.elte.eserial.mapper;

/**
 * Common interface for the different object mappers.
 */
interface ObjectMapper {

    /**
     * Returns the mapped representation of {@code that} depending on its type.
     *
     * @param that an arbitrary object
     * @return mapped representation of {@code that}
     */
    Object map(Object that);
}
