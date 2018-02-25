package hu.elte.eserial.util;

import hu.elte.eserial.annotation.ExcludeThis;
import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.MaxDepth;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AnnotationUtilsTest {

    @Test
    public void testCompare_SortsAnnotationsByTheirPriorities() {
        //ExcludeThis == MaxDepth > IncludeElements > IncludeMatching
        ExcludeThis excludeThis = new ExcludeThis() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return ExcludeThis.class;
            }
        };
        IncludeElements includeElements = new IncludeElements() {
            @Override
            public String[] value() {
                return new String[0];
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return IncludeElements.class;
            }
        };
        IncludeMatching includeMatching = new IncludeMatching() {
            @Override
            public IncludeMatcher[] value() {
                return new IncludeMatcher[0];
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return IncludeMatching.class;
            }
        };
        MaxDepth maxDepth = new MaxDepth() {
            @Override
            public int value() {
                return 0;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MaxDepth.class;
            }
        };

        List<Annotation> eserialAnnotations = Arrays.asList(excludeThis, includeElements, includeMatching, maxDepth);
        eserialAnnotations.sort(AnnotationUtils::compare);

        int exludeThisIndex = eserialAnnotations.indexOf(excludeThis);
        int includeElementsIndex = eserialAnnotations.indexOf(includeElements);
        int includeMatchingIndex = eserialAnnotations.indexOf(includeMatching);
        int maxDepthIndex = eserialAnnotations.indexOf(maxDepth);

        assertTrue(exludeThisIndex < includeElementsIndex);
        assertTrue(maxDepthIndex < includeElementsIndex);
        assertTrue(includeElementsIndex < includeMatchingIndex);
    }


}
