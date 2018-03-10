package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.EserialAnnotation;
import hu.elte.eserial.annotation.ExcludeThis;
import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.exception.EserialMissingInclusionRuleException;

import java.lang.annotation.Annotation;

/**
 * A simple factory for constructing {@link AbstractInclusionRule} descendants.
 */
public class InclusionRuleFactory {

    /**
     * Constructs an {@link AbstractInclusionRule} descendant.
     *
     * @param annotation an {@link EserialAnnotation} annotation
     * @return an {@link AbstractInclusionRule} for the given {@code annotation}
     *
     * @exception EserialMissingInclusionRuleException if the given {@code annotation} is null or
     *              no {@link AbstractInclusionRule} exists for that {@link EserialAnnotation}
     */
    public static AbstractInclusionRule get(Annotation annotation) {

        if (annotation instanceof ExcludeThis) {
            return new ExcludeThisInclusionRule((ExcludeThis)annotation);
        }
        else if (annotation instanceof IncludeElements) {
            return new IncludeElementsInclusionRule((IncludeElements)annotation);
        }
        else if (annotation instanceof IncludeMatching) {
            return new IncludeMatchingInclusionRule((IncludeMatching)annotation);
        }
        else if (annotation == null) {
            throw new EserialMissingInclusionRuleException(null);
        }
        else {
            throw new EserialMissingInclusionRuleException(annotation.annotationType().getSimpleName());
        }
    }
}
