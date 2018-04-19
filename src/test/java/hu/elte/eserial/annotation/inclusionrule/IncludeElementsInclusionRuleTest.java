package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.model.EserialElement;
import hu.elte.eserial.model.Getter;
import hu.elte.eserial.model.Setter;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IncludeElementsInclusionRuleTest {

    @IncludeElements("name")
    public class IncludeElementsName {
        public String getName() { return null; }
        public void setName(String name) {}
        public Integer getAge() { return null; }
        public void setAge(Integer age) {}
    }

    private IncludeElementsInclusionRule inclusionRule =
            new IncludeElementsInclusionRule(IncludeElementsName.class.getDeclaredAnnotation(IncludeElements.class));

    private EserialElement createElementGetter(String methodName) throws NoSuchMethodException {
        Getter getter = new Getter(null, IncludeElementsName.class.getDeclaredMethod(methodName));
        return EserialElement.fromAccessor(getter);
    }

    private EserialElement createElementSetter(String methodName, Class paramType) throws NoSuchMethodException {
        Setter setter = new Setter(null, IncludeElementsName.class.getDeclaredMethod(methodName, paramType));
        return EserialElement.fromAccessor(setter);
    }

    @Test
    public void shouldInclude_GivenName_ReturnsTrue() throws NoSuchMethodException {
        assertTrue(inclusionRule.evaluate(createElementGetter("getName")));
        assertTrue(inclusionRule.evaluate(createElementSetter("setName", String.class)));
    }

    @Test
    public void shouldInclude_GivenAge_ReturnsFalse() throws NoSuchMethodException {
        assertFalse(inclusionRule.evaluate(createElementGetter("getAge")));
        assertFalse(inclusionRule.evaluate(createElementSetter("setAge", Integer.class)));
    }

    @Test
    public void isPositiveRule_ReturnsTrue() {
        assertTrue(inclusionRule.isInclusionRule());
    }
}
