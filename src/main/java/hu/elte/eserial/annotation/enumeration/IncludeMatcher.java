package hu.elte.eserial.annotation.enumeration;

import hu.elte.eserial.annotation.IncludeMatching;

/**
 * Used by the {@link IncludeMatching} annotation.<br>
 * {@link IncludeMatcher#NOT_NULL} makes an object's null values ignored when serializing,<br>
 * {@link IncludeMatcher#NOT_EMPTY} makes an object's empty values ignored.
 */
public enum IncludeMatcher {
    NOT_NULL,
    NOT_EMPTY
}
