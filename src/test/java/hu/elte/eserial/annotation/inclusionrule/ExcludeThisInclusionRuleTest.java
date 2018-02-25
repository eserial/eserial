package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.ExcludeThis;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.HashMap;

import static hu.elte.eserial.testutil.util.EserialElementCreator.withDummyGetter;
import static org.junit.Assert.assertFalse;

public class ExcludeThisInclusionRuleTest {

    private static ExcludeThisInclusionRule dummyExcludeThisInclusionRule() {
        return new ExcludeThisInclusionRule(new ExcludeThis() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return ExcludeThis.class;
            }
        });
    }

    @Test
    public void testShouldInclude_GivenAnything_ReturnsFalse() {
        ExcludeThisInclusionRule inclusionRule = dummyExcludeThisInclusionRule();
        assertFalse(inclusionRule.evaluate(withDummyGetter(null), new HashMap<>()));
    }

    @Test
    public void testIsPositiveRule_ReturnsFalse() {
        assertFalse(dummyExcludeThisInclusionRule().isInclusionRule());
    }
}
