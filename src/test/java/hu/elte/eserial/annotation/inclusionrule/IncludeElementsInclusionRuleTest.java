package hu.elte.eserial.annotation.inclusionrule;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.HashMap;

import static hu.elte.eserial.testutil.util.EserialElementCreator.withNamedGetter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IncludeElementsInclusionRuleTest {

    private static IncludeElementsInclusionRule dummyIncludeFieldsInclusionRule() {
        return new IncludeElementsInclusionRule(new hu.elte.eserial.annotation.IncludeElements() {
            @Override
            public String[] value() {
                return new String[] {"name"};
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return hu.elte.eserial.annotation.IncludeElements.class;
            }
        });
    }

    @Test
    public void testShouldInclude_GivenName_ReturnsTrue() {
        IncludeElementsInclusionRule inclusionRule = dummyIncludeFieldsInclusionRule();
        assertTrue(inclusionRule.evaluate(withNamedGetter("getName", null), new HashMap<>()));
    }

    @Test
    public void testShouldInclude_GivenAge_ReturnsFalse() {
        IncludeElementsInclusionRule inclusionRule = dummyIncludeFieldsInclusionRule();
        assertFalse(inclusionRule.evaluate(withNamedGetter("getAge", null), new HashMap<>()));
    }


    @Test
    public void  testIsPositiveRule_ReturnsTrue() {
        assertTrue(dummyIncludeFieldsInclusionRule().isInclusionRule());
    }
}
