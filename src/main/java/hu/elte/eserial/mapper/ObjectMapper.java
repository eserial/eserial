package hu.elte.eserial.mapper;

import hu.elte.eserial.recursion.RecursionChecker;

/**
 * Common interface for the different object mappers.
 */
interface ObjectMapper {

    /**
     * Returns the mapped representation of {@code that} depending on its type.
     *
     * @param {@code that} an arbitrary object
     * @param recursionChecker a recursion checker instance or {@code null} if this element is considered
     *                         the root of a compound object
     * @return mapped representation of {@code that}
     */
    Object map(Object that, RecursionChecker recursionChecker);
}
