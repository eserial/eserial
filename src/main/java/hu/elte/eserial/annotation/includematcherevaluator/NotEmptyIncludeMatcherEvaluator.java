package hu.elte.eserial.annotation.includematcherevaluator;

import hu.elte.eserial.annotation.enumeration.IncludeMatcher;

import java.util.Collection;

/**
 * Evaluates {@link IncludeMatcher#NOT_EMPTY} constraints.
 * @see IncludeMatcherEvaluator
 */
public class NotEmptyIncludeMatcherEvaluator implements IncludeMatcherEvaluator {

    /**
     * @param value {@inheritDoc}
     * @return {@code true} if the given {@code value} is
     * <ul>
     *     <li>a {@link Collection} and is not empty</li>
     *     <li>a {@link String} and is not empty</li>
     *     <li>of another type and is not null</li>
     * </ul>
     * @see IncludeMatcherEvaluator#evaluate(Object)
     */
    @Override
    public boolean evaluate(Object value) {
        if (value instanceof Collection) {
            return !((Collection)value).isEmpty();
        }
        else if (value instanceof String) {
            return !((String)value).isEmpty();
        }
        else {
            return value != null;
        }
    }
}
