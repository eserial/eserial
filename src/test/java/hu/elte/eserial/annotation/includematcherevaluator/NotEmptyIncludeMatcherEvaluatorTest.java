package hu.elte.eserial.annotation.includematcherevaluator;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotEmptyIncludeMatcherEvaluatorTest {

    @Test
    public void testEvaluate_GivenEmptyString_ReturnsFalse() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertFalse(evaluator.evaluate(""));
    }

    @Test
    public void testEvaluate_GivenNotEmptyString_ReturnsTrue() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertTrue(evaluator.evaluate("a"));
    }

    @Test
    public void testEvaluate_GivenEmptyCollection_ReturnsFalse() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertFalse(evaluator.evaluate(Collections.emptyList()));
    }

    @Test
    public void testEvaluate_GivenNotEmptyCollection_ReturnsTrue() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertTrue(evaluator.evaluate(Collections.singletonList(new Object())));
    }

    @Test()
    public void testEvaluate_GivenNull_ReturnsFalse() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertFalse(evaluator.evaluate(null));
    }
}
