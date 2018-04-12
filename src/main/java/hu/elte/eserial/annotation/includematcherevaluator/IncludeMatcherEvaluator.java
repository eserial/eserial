package hu.elte.eserial.annotation.includematcherevaluator;

import hu.elte.eserial.annotation.IncludeMatching;

/**
 * An interface for evaluating {@link IncludeMatching} constraints.
 */
public interface IncludeMatcherEvaluator {
    /**
     * @param value a value of an object which will be evaluated
     * @return {@code true} if the corresponding element should be included according
     * to the {@link IncludeMatching} annotation on its class
     */
    boolean evaluate(Object value);
}
