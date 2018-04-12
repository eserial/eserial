package hu.elte.eserial.annotation.includematcherevaluator;

import hu.elte.eserial.annotation.enumeration.IncludeMatcher;

/**
 * Evaluates {@link IncludeMatcher#NOT_NULL} constraints.
 * @see IncludeMatcherEvaluator
 */
public class NotNullIncludeMatcherEvaluator implements IncludeMatcherEvaluator {

    /**
     * @param value {@inheritDoc}
     * @return {@code true} if the given {@code value} is not {@code null}
     * @see IncludeMatcherEvaluator#evaluate(Object)
     */
    @Override
    public boolean evaluate(Object value) {
        return value != null;
    }
}
