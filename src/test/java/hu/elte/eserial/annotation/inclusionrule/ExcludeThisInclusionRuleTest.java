package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.ExcludeThis;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertFalse;

public class ExcludeThisInclusionRuleTest {


    @Test
    public void shouldInclude_GivenAnything_ReturnsFalse() {
        assertFalse(new ExcludeThisInclusionRule(null).evaluate(null));
    }

    @Test
    public void isPositiveRule_ReturnsFalse() {
        assertFalse(new ExcludeThisInclusionRule(null).isInclusionRule());
    }
}
