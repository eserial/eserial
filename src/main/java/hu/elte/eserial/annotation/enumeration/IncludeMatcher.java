package hu.elte.eserial.annotation.enumeration;

import hu.elte.eserial.annotation.IncludeMatching;

/**
 * Used by the {@link IncludeMatching} annotation.<br>
 * {@link IncludeMatcher#NotNull} makes an object's null values ignored when serializing,<br>
 * {@link IncludeMatcher#NotEmpty} makes an object's empty values ignored.
 */
public enum IncludeMatcher {
    NotNull,
    NotEmpty
}
