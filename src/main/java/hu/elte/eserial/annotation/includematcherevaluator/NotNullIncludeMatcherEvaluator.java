package hu.elte.eserial.annotation.includematcherevaluator;

import hu.elte.eserial.annotation.enumeration.IncludeMatcher;

/**
 * Evaluates {@link IncludeMatcher#NotNull} constraints.
 * @see AbstractIncludeMatcherEvaluator
 */
public class NotNullIncludeMatcherEvaluator extends AbstractIncludeMatcherEvaluator {

    /**
     * @param value {@inheritDoc}
     * @return {@code true} if the given {@code value} is not {@code null}
     * @see AbstractIncludeMatcherEvaluator#evaluate(Object)
     */
    @Override
    public boolean evaluate(Object value) {
        return value != null;
    }
}
