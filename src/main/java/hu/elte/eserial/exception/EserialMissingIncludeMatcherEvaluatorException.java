package hu.elte.eserial.exception;

import hu.elte.eserial.annotation.includematcherevaluator.IncludeMatcherEvaluator;

/**
 * Thrown when annotation processing is interrupted due to a missing {@link IncludeMatcherEvaluator}.
 */
public class EserialMissingIncludeMatcherEvaluatorException extends EserialException {

    public EserialMissingIncludeMatcherEvaluatorException(String includeMatcherName) {
        super(String.format("No IncludeMatcherEvaluator found for %s.", includeMatcherName));
    }
}
