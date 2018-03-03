package hu.elte.eserial.exception;

import hu.elte.eserial.annotation.inclusionrule.AbstractInclusionRule;

/**
 * Thrown when annotation processing is interrupted due to a missing {@link AbstractInclusionRule}.
 */
public class EserialMissingInclusionRuleException extends EserialException {

    public EserialMissingInclusionRuleException(String annotationType) {
        super(String.format("No InclusionRule found for %s.", annotationType));
    }
}
