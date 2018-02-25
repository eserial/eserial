package hu.elte.eserial.annotation.includematcherevaluator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotNullIncludeMatcherEvaluatorTest {

    @Test
    public void testEvaluate_GivenNull_ReturnsFalse() {
        NotNullIncludeMatcherEvaluator evaluator = new NotNullIncludeMatcherEvaluator();
        assertFalse(evaluator.evaluate(null));
    }

    @Test
    public void testEvaluate_GivenNotNull_ReturnsTrue() {
        NotNullIncludeMatcherEvaluator evaluator = new NotNullIncludeMatcherEvaluator();
        assertTrue(evaluator.evaluate(new Object()));
    }
}
