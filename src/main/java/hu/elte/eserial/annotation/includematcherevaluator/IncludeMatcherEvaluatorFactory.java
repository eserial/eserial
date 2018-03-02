package hu.elte.eserial.annotation.includematcherevaluator;

import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;

/**
 * A simple factory for constructing {@link AbstractIncludeMatcherEvaluator} descendants.
 */
public class IncludeMatcherEvaluatorFactory {

    /**
     * Prevents accidental instantiation.
     */
    private IncludeMatcherEvaluatorFactory() {}

    /**
     * Constructs an {@link AbstractIncludeMatcherEvaluator} descendant.
     *
     * @param includeMatcher an {@link IncludeMatcher} in an {@link IncludeMatching} annotation
     * @return an {@link AbstractIncludeMatcherEvaluator} for the given {@code includeMatcher}
     *
     * @exception IllegalArgumentException if the given {@code includeMatcher} is {@code null} or
     *              no {@link AbstractIncludeMatcherEvaluator} exists for that {@link IncludeMatcher}
     */
    public static AbstractIncludeMatcherEvaluator get(IncludeMatcher includeMatcher) {
        if (IncludeMatcher.NotNull.equals(includeMatcher)) {
            return new NotNullIncludeMatcherEvaluator();
        }
        else if (IncludeMatcher.NotEmpty.equals(includeMatcher)) {
            return new NotEmptyIncludeMatcherEvaluator();
        }
        else if (includeMatcher == null) {
            throw new IllegalArgumentException("No IncludeMatcher for null.");
        }
        else {
            throw new IllegalArgumentException("No IncludeMatcher for: " + includeMatcher.name());
        }
    }
}
