package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.ExcludeThis;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertFalse;

public class ExcludeThisInclusionRuleTest {

    private ExcludeThisInclusionRule excludeThisInclusionRule = new ExcludeThisInclusionRule(new ExcludeThis() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return ExcludeThis.class;
        }
    });

    @Test
    public void shouldInclude_GivenAnything_ReturnsFalse() {
        ExcludeThisInclusionRule inclusionRule = excludeThisInclusionRule;
        assertFalse(inclusionRule.evaluate(null));
    }

    @Test
    public void isPositiveRule_ReturnsFalse() {
        assertFalse(excludeThisInclusionRule.isInclusionRule());
    }
}
