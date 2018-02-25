package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.MaxDepth;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import static hu.elte.eserial.testutil.util.EserialElementCreator.withDummyGetter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaxDepthInclusionRuleTest {

    private static MaxDepthInclusionRule maxDepthWithValue1() {
        return new MaxDepthInclusionRule(new MaxDepth() {
            @Override
            public int value() {
                return 1;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MaxDepth.class;
            }
        });
    }

    @Test
    public void testShouldInclude_GivenEmptyMap_ReturnsTrue() {
        MaxDepthInclusionRule inclusionRule = maxDepthWithValue1();
        assertTrue(inclusionRule.evaluate(withDummyGetter(new Object()), new HashMap<>()));
    }

    @Test
    public void testShouldInclude_GivenOneLayerAndPrimitive_ReturnsTrue() {
        MaxDepthInclusionRule inclusionRule = maxDepthWithValue1();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "a");

        assertTrue(inclusionRule.evaluate(withDummyGetter(5), map1));
    }

    @Test
    public void testShouldInclude_GivenOneLayerAndNull_ReturnsTrue() {
        MaxDepthInclusionRule inclusionRule = maxDepthWithValue1();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "a");

        assertTrue(inclusionRule.evaluate(withDummyGetter(null), map1));
    }

    @Test
    public void testShouldInclude_GivenOneLayerAndNestedObject_ReturnsFalse() {
        MaxDepthInclusionRule inclusionRule = maxDepthWithValue1();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "a");
        Map<String, Object> map2 = new HashMap<>();
        map1.put("token", "b");

        assertFalse(inclusionRule.evaluate(withDummyGetter(map2), map1));
    }

    @Test
    public void  testIsPositiveRule_ReturnsFalse() {
        assertFalse(maxDepthWithValue1().isInclusionRule());
    }
}
