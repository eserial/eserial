package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.recursion.model.EserialElement;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IncludeElementsInclusionRuleTest {

    public class IncludeElementsName {
        public String getName() { return null; }
        public Integer getAge() { return null; }
    }

    private IncludeElementsInclusionRule includeElementsInclusionRule = new IncludeElementsInclusionRule(
            new IncludeElements() {
                @Override
                public String[] value() {
            return new String[] {"name"};
        }

                @Override
                public Class<? extends Annotation> annotationType() {
                    return hu.elte.eserial.annotation.IncludeElements.class;
                }
            });

    @Test
    public void shouldInclude_GivenName_ReturnsTrue() throws NoSuchMethodException {
        assertTrue(includeElementsInclusionRule.evaluate(
                new EserialElement(IncludeElementsName.class.getDeclaredMethod("getName"), null)));
    }

    @Test
    public void shouldInclude_GivenAge_ReturnsFalse() throws NoSuchMethodException {
        assertFalse(includeElementsInclusionRule.evaluate(
                new EserialElement(IncludeElementsName.class.getDeclaredMethod("getAge"), null)));
    }


    @Test
    public void isPositiveRule_ReturnsTrue() {
        assertTrue(includeElementsInclusionRule.isInclusionRule());
    }
}
