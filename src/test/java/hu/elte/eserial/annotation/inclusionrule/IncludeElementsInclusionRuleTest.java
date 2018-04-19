package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.model.EserialElement;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IncludeElementsInclusionRuleTest {

    @IncludeElements("name")
    public class IncludeElementsName {
        public String getName() { return null; }
        public Integer getAge() { return null; }
    }

    private IncludeElementsInclusionRule inclusionRule =
            new IncludeElementsInclusionRule(IncludeElementsName.class.getDeclaredAnnotation(IncludeElements.class));

    @Test
    public void shouldInclude_GivenName_ReturnsTrue() throws NoSuchMethodException {
        assertTrue(inclusionRule.evaluate(
                new EserialElement(IncludeElementsName.class.getDeclaredMethod("getName"), null)));
    }

    @Test
    public void shouldInclude_GivenAge_ReturnsFalse() throws NoSuchMethodException {
        assertFalse(inclusionRule.evaluate(
                new EserialElement(IncludeElementsName.class.getDeclaredMethod("getAge"), null)));
    }

    @Test
    public void isPositiveRule_ReturnsTrue() {
        assertTrue(inclusionRule.isInclusionRule());
    }
}
