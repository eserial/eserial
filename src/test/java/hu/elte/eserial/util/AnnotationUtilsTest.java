package hu.elte.eserial.util;

import hu.elte.eserial.annotation.ExcludeThis;
import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import hu.elte.eserial.exception.EserialNotEserialAnnotationException;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import static hu.elte.eserial.annotation.enumeration.EserialAnnotationType.Formatting;
import static hu.elte.eserial.annotation.enumeration.EserialAnnotationType.Inclusion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnnotationUtilsTest {

    private ExcludeThis excludeThis = new ExcludeThis() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return ExcludeThis.class;
        }
    };
    private IncludeElements includeElements = new IncludeElements() {
        @Override
        public String[] value() {
            return new String[0];
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return IncludeElements.class;
        }
    };
    private IncludeMatching includeMatching = new IncludeMatching() {
        @Override
        public IncludeMatcher[] value() {
            return new IncludeMatcher[0];
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return IncludeMatching.class;
        }
    };
    private Test test = new Test() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return Test.class;
        }

        @Override
        public Class<? extends Throwable> expected() {
            return null;
        }

        @Override
        public long timeout() {
            return 0;
        }
    };

    @Test
    public void compare_SortsAnnotationsByTheirPriorities() {
        //ExcludeThis == MaxDepth > IncludeElements > IncludeMatching
        List<Annotation> eserialAnnotations = Arrays.asList(excludeThis, includeElements, includeMatching);
        eserialAnnotations.sort(AnnotationUtils::compare);

        int exludeThisIndex = eserialAnnotations.indexOf(excludeThis);
        int includeElementsIndex = eserialAnnotations.indexOf(includeElements);
        int includeMatchingIndex = eserialAnnotations.indexOf(includeMatching);

        assertTrue(exludeThisIndex < includeElementsIndex);
        assertTrue(includeElementsIndex < includeMatchingIndex);
    }

    @Test(expected = EserialNotEserialAnnotationException.class)
    public void compare_GivenLhsNonEserialAnnotation_EserialNotEserialAnnotationException() {
        AnnotationUtils.compare(test, excludeThis);
    }

    @Test(expected = EserialNotEserialAnnotationException.class)
    public void compare_GivenRhsNonEserialAnnotation_EserialNotEserialAnnotationException() {
        AnnotationUtils.compare(excludeThis, test);
    }

    @Test(expected = NullPointerException.class)
    public void isEserialAnnotation_GivenNull_ThrowsNullPointerException() {
        AnnotationUtils.isEserialAnnotation(null);
    }

    @Test
    public void isEserialAnnotation_GivenEserialAnnotation_ReturnsTrue() {
        assertTrue(AnnotationUtils.isEserialAnnotation(excludeThis));
    }

    @Test
    public void isEserialAnnotation_GivenOtherAnnotation_ReturnsFalse() {
        assertFalse(AnnotationUtils.isEserialAnnotation(test));
    }

    @Test(expected = NullPointerException.class)
    public void isMatchingEserialAnnotation_GivenNullAndValidType_ThrowsNullPointerException() {
        AnnotationUtils.isMatchingEserialAnnotation(null, Inclusion);
    }

    @Test
    public void isMatchingEserialAnnotation_GivenExcludeThisAndInclusionType_ReturnsTrue() {
        assertTrue(AnnotationUtils.isMatchingEserialAnnotation(excludeThis, Inclusion));
    }

    @Test
    public void isMatchingEserialAnnotation_GivenExcludeThisAndFormattingType_ReturnsFalse() {
        assertFalse(AnnotationUtils.isMatchingEserialAnnotation(excludeThis, Formatting));
    }

    @Test
    public void isEserialAnnotation_GivenOtherAnnotationAndValidType_ReturnsFalse() {
        assertFalse(AnnotationUtils.isMatchingEserialAnnotation(test, Inclusion));
    }

    @Test
    public void getEserialAnnotations_GivenMixedList_ReturnsListOfEserialAnnotations() {
        List<Annotation> annotations =
                AnnotationUtils.getEserialAnnotations(Arrays.asList(excludeThis, test, includeElements),
                        Inclusion);
        assertEquals(2, annotations.size());
        assertTrue(annotations.contains(excludeThis));
        assertFalse(annotations.contains(test));
        assertTrue(annotations.contains(includeElements));
    }

    @IncludeElements("placeholder")
    public class WithIncludeElements {}

    @Test
    public void getEserialAnnotations_GivenClassWithIncludeElements_ReturnsIncludeElements() {
        List<Annotation> annotations = AnnotationUtils.getEserialAnnotations(WithIncludeElements.class, Inclusion);
        assertEquals(1, annotations.size());
        assertTrue(annotations.get(0) instanceof IncludeElements);
    }

    public class WithExcludeThisOnField {
        @ExcludeThis
        public String onField;
    }

    @Test
    public void getEserialAnnotations_GivenFieldWithExcludeThis_ReturnsExcludeThis() throws NoSuchFieldException {
        List<Annotation> annotations = AnnotationUtils.getEserialAnnotations(
                WithExcludeThisOnField.class.getDeclaredField("onField"), Inclusion);
        assertEquals(1, annotations.size());
        assertTrue(annotations.get(0) instanceof ExcludeThis);
    }

    public class WithExcludeThisOnGetter {
        @ExcludeThis
        public String onGetter() { return null; }
    }

    @Test
    public void getEserialAnnotations_GivenGetterWithExcludeThis_ReturnsExcludeThis() throws NoSuchMethodException {
        List<Annotation> annotations = AnnotationUtils.getEserialAnnotations(
                WithExcludeThisOnGetter.class.getDeclaredMethod("onGetter"), Inclusion);
        assertEquals(1, annotations.size());
        assertTrue(annotations.get(0) instanceof ExcludeThis);
    }
}
