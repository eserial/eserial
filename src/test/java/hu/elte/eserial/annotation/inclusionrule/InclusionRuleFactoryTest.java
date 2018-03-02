package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.ExcludeThis;
import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertTrue;

public class InclusionRuleFactoryTest {

    @Test
    public void get_GivenIgnoreThis_ReturnsAnIgnoreThisInclusionRule() {
        AbstractInclusionRule rule = InclusionRuleFactory.get(new ExcludeThis(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return ExcludeThis.class;
            }
        });
        assertTrue(rule instanceof ExcludeThisInclusionRule);
    }

    @Test
    public void get_GivenIncludeFields_ReturnsAnIncludeFieldsInclusionRule() {
        AbstractInclusionRule rule = InclusionRuleFactory.get(new IncludeElements(){
            @Override
            public String[] value() {
                return new String[0];
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return IncludeElements.class;
            }
        });
        assertTrue(rule instanceof IncludeElementsInclusionRule);
    }

    @Test
    public void get_GivenMatching_ReturnsAnIncludeMatchingInclusionRule() {
        AbstractInclusionRule rule = InclusionRuleFactory.get(new IncludeMatching(){
            @Override
            public IncludeMatcher[] value() {
                return new IncludeMatcher[0];
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return IncludeMatching.class;
            }
        });
        assertTrue(rule instanceof IncludeMatchingInclusionRule);
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_GivenNull_ThrowsIllegalArgumentException() {
        InclusionRuleFactory.get(null);
    }
}
