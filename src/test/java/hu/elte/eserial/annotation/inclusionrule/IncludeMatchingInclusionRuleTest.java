package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.HashMap;

import static hu.elte.eserial.testutil.util.EserialElementCreator.withNamedGetter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IncludeMatchingInclusionRuleTest {

    private static IncludeMatchingInclusionRule withNotNullAndNotEmptyIncludeMatcher() {
        return new IncludeMatchingInclusionRule(new IncludeMatching() {
            @Override
            public IncludeMatcher[] value() {
                return new IncludeMatcher[] {IncludeMatcher.NotNull, IncludeMatcher.NotEmpty};
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return IncludeMatching.class;
            }
        });
    }

    @Test
    public void testShouldInclude_GivenA_ReturnsTrue() {
        IncludeMatchingInclusionRule inclusionRule = withNotNullAndNotEmptyIncludeMatcher();
        assertTrue(inclusionRule.evaluate(withNamedGetter("getName", "a"), new HashMap<>()));
    }

    @Test
    public void testShouldInclude_GivenEmptyString_ReturnsFalse() {
        IncludeMatchingInclusionRule inclusionRule = withNotNullAndNotEmptyIncludeMatcher();
        assertFalse(inclusionRule.evaluate(withNamedGetter("getName", ""), new HashMap<>()));
    }

    @Test
    public void testShouldInclude_GivenNull_ReturnsFalse() {
        IncludeMatchingInclusionRule inclusionRule = withNotNullAndNotEmptyIncludeMatcher();
        assertFalse(inclusionRule.evaluate(withNamedGetter("getName", null), new HashMap<>()));
    }


    @Test
    public void  testIsPositiveRule_ReturnsFalse() {
        assertFalse(withNotNullAndNotEmptyIncludeMatcher().isInclusionRule());
    }
}
