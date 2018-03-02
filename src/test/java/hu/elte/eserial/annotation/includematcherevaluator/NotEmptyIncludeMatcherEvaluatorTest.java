package hu.elte.eserial.annotation.includematcherevaluator;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotEmptyIncludeMatcherEvaluatorTest {

    @Test
    public void evaluate_GivenEmptyString_ReturnsFalse() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertFalse(evaluator.evaluate(""));
    }

    @Test
    public void evaluate_GivenNotEmptyString_ReturnsTrue() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertTrue(evaluator.evaluate("a"));
    }

    @Test
    public void evaluate_GivenEmptyCollection_ReturnsFalse() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertFalse(evaluator.evaluate(Collections.emptyList()));
    }

    @Test
    public void evaluate_GivenNotEmptyCollection_ReturnsTrue() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertTrue(evaluator.evaluate(Collections.singletonList(new Object())));
    }

    @Test()
    public void evaluate_GivenNull_ReturnsFalse() {
        NotEmptyIncludeMatcherEvaluator evaluator = new NotEmptyIncludeMatcherEvaluator();
        assertFalse(evaluator.evaluate(null));
    }
}
