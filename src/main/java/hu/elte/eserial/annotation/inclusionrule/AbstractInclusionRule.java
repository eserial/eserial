package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.EserialAnnotation;
import hu.elte.eserial.recursion.model.EserialElement;

import java.util.Map;

/**
 * An abstract class for evaluating {@link EserialAnnotation} constraints
 * @param <A> the type of annotation this rule applies to
 */
public abstract class AbstractInclusionRule<A> {

    protected A annotation;

    /**
     * Constructs an {@link AbstractInclusionRule} descendant and sets the {@code annotation} for it.
     * @param annotation the annotation to be used in the {@link AbstractInclusionRule#evaluate} method
     */
    AbstractInclusionRule(A annotation) {
        this.annotation = annotation;
    }

    /**
     * An element of an object which will be evaluated.
     * @param element an element containing the getter or setter to be checked and the value of the field
     * @param objectMap the built map of the object until now
     * @return {@code true} if the element should be included according to the {@link EserialAnnotation}
     */
    public abstract boolean evaluate(EserialElement element, Map<String, Object> objectMap);

    /**
     * Describes whether this rule is an inclusion or an exclusion rule.<br/>
     * <br/>
     * For inclusion rules the processing should be stopped when the {@link AbstractInclusionRule#evaluate}
     * returns <b>true</b>.<br/>
     * For exclusion rules the processing should be stopped when the {@link AbstractInclusionRule#evaluate}
     * returns <b>false</b>.<br/>
     * @return {@code true} if this is an inclusion rule, {@code false} if it's an exclusion rule
     */
    public abstract boolean isInclusionRule();
}
