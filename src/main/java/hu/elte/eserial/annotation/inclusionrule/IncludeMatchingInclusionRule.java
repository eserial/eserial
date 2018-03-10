package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import hu.elte.eserial.annotation.includematcherevaluator.AbstractIncludeMatcherEvaluator;
import hu.elte.eserial.annotation.includematcherevaluator.IncludeMatcherEvaluatorFactory;
import hu.elte.eserial.recursion.model.EserialElement;

/**
 * Evaluates {@link IncludeMatching} annotations.
 * @see AbstractInclusionRule
 */
public class IncludeMatchingInclusionRule extends AbstractInclusionRule<IncludeMatching> {

    IncludeMatchingInclusionRule(IncludeMatching annotation) {
        super(annotation);
    }

    /**
     * Every {@link IncludeMatcher} will be evaluated on the given value.
     * @param element {@inheritDoc}
     * @return {@code true} if <b>all</b> {@link IncludeMatcher}s enable the element to be included
     */
    @Override
    public boolean evaluate(EserialElement element) {
        boolean shouldInclude = true;
        for (IncludeMatcher includeMatcher : annotation.value()) {
            AbstractIncludeMatcherEvaluator evaluator = IncludeMatcherEvaluatorFactory.get(includeMatcher);
            shouldInclude &= evaluator.evaluate(element.getValue());
        }
        return shouldInclude;
    }

    @Override
    public boolean isInclusionRule() {
        return false;
    }
}
