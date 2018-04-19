package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import hu.elte.eserial.model.EserialElement;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IncludeMatchingInclusionRuleTest {

    private IncludeMatchingInclusionRule notNullAndNotEmptyIncludeMatcher =
            new IncludeMatchingInclusionRule(new IncludeMatching() {
                @Override
                public IncludeMatcher[] value() {
                    return new IncludeMatcher[] {IncludeMatcher.NOT_NULL, IncludeMatcher.NOT_EMPTY};
                }

                @Override
                public Class<? extends Annotation> annotationType() {
                    return IncludeMatching.class;
                }
            });

    @Test
    public void shouldInclude_GivenA_ReturnsTrue() {
        assertTrue(notNullAndNotEmptyIncludeMatcher.evaluate(new EserialElement(null, "a")));
    }

    @Test
    public void shouldInclude_GivenEmptyString_ReturnsFalse() {
        assertFalse(notNullAndNotEmptyIncludeMatcher.evaluate(new EserialElement(null, "")));
    }

    @Test
    public void shouldInclude_GivenNull_ReturnsFalse() {
        assertFalse(notNullAndNotEmptyIncludeMatcher.evaluate(new EserialElement(null, null)));
    }

    @Test
    public void isPositiveRule_ReturnsFalse() {
        assertFalse(notNullAndNotEmptyIncludeMatcher.isInclusionRule());
    }
}
