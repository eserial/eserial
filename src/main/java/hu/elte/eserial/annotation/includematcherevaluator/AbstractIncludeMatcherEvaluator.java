package hu.elte.eserial.annotation.includematcherevaluator;

import hu.elte.eserial.annotation.IncludeMatching;

/**
 * An abstract class for evaluating {@link IncludeMatching} constraints.
 */
public abstract class AbstractIncludeMatcherEvaluator {
    /**
     * @param value a value of an object which will be evaluated
     * @return {@code true} if the corresponding element should be included according
     * to the {@link IncludeMatching} annotation on its class
     */
    public abstract boolean evaluate(Object value);
}
