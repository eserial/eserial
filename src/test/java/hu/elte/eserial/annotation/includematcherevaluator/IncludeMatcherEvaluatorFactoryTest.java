package hu.elte.eserial.annotation.includematcherevaluator;

import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IncludeMatcherEvaluatorFactoryTest {

    @Test
    public void get_GivenNotNull_ReturnsANotNullIncludeMatcherEvaluator() {
        AbstractIncludeMatcherEvaluator evaluator = IncludeMatcherEvaluatorFactory.get(IncludeMatcher.NotNull);
        assertTrue(evaluator instanceof NotNullIncludeMatcherEvaluator);
    }

    @Test
    public void get_GivenNotEmpty_ReturnsANotEmptyIncludeMatcherEvaluator() {
        AbstractIncludeMatcherEvaluator evaluator = IncludeMatcherEvaluatorFactory.get(IncludeMatcher.NotEmpty);
        assertTrue(evaluator instanceof NotEmptyIncludeMatcherEvaluator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_GivenNull_ThrowsIllegalArgumentException() {
        IncludeMatcherEvaluatorFactory.get(null);
    }
}
