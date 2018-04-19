package hu.elte.eserial.util;

import hu.elte.eserial.annotation.ExcludeThis;
import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import hu.elte.eserial.exception.EserialNotEserialAnnotationException;
import hu.elte.eserial.model.EserialElement;
import hu.elte.eserial.model.Getter;
import hu.elte.eserial.model.Setter;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import static hu.elte.eserial.annotation.enumeration.EserialAnnotationType.FORMATTING;
import static hu.elte.eserial.annotation.enumeration.EserialAnnotationType.INCLUSION;
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

    private EserialElement createElementGetter(Object object, String methodName) throws NoSuchMethodException {
        Getter getter = new Getter(object, object.getClass().getDeclaredMethod(methodName));
        return EserialElement.fromAccessorAndValue(getter, getter.evaluate());
    }

    private EserialElement createElementSetter(Object object, String methodName, Class paramType) throws NoSuchMethodException {
        Setter setter = new Setter(object, object.getClass().getDeclaredMethod(methodName, paramType));
        return EserialElement.fromAccessor(setter);
    }

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
        AnnotationUtils.isMatchingEserialAnnotation(null, INCLUSION);
    }

    @Test
    public void isMatchingEserialAnnotation_GivenExcludeThisAndInclusionType_ReturnsTrue() {
        assertTrue(AnnotationUtils.isMatchingEserialAnnotation(excludeThis, INCLUSION));
    }

    @Test
    public void isMatchingEserialAnnotation_GivenExcludeThisAndFormattingType_ReturnsFalse() {
        assertFalse(AnnotationUtils.isMatchingEserialAnnotation(excludeThis, FORMATTING));
    }

    @Test
    public void isEserialAnnotation_GivenOtherAnnotationAndValidType_ReturnsFalse() {
        assertFalse(AnnotationUtils.isMatchingEserialAnnotation(test, INCLUSION));
    }

    @Test
    public void getEserialAnnotations_GivenMixedList_ReturnsListOfEserialAnnotations() {
        List<Annotation> annotations =
                AnnotationUtils.getEserialAnnotations(Arrays.asList(excludeThis, test, includeElements),
                        INCLUSION);
        assertEquals(2, annotations.size());
        assertTrue(annotations.contains(excludeThis));
        assertFalse(annotations.contains(test));
        assertTrue(annotations.contains(includeElements));
    }

    @IncludeElements("placeholder")
    public class WithIncludeElements {}

    @Test
    public void getEserialAnnotations_GivenClassWithIncludeElements_ReturnsIncludeElements() {
        List<Annotation> annotations = AnnotationUtils.getEserialAnnotations(WithIncludeElements.class, INCLUSION);
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
                WithExcludeThisOnField.class.getDeclaredField("onField"), INCLUSION);
        assertEquals(1, annotations.size());
        assertTrue(annotations.get(0) instanceof ExcludeThis);
    }

    public class WithExcludeThisOnGetter {
        @ExcludeThis
        public String onGetter() { return null; }
    }

    @Test
    public void getEserialAnnotations_GivenGetterWithExcludeThis_ReturnsExcludeThis() throws NoSuchMethodException {
        Getter getter = new Getter(null, WithExcludeThisOnGetter.class.getDeclaredMethod("onGetter"));
        List<Annotation> annotations = AnnotationUtils.getEserialAnnotations(getter, INCLUSION);
        assertEquals(1, annotations.size());
        assertTrue(annotations.get(0) instanceof ExcludeThis);
    }

    public class ExcludeThisDummy {

        private String fieldIncluded = "fieldIncludedValue";

        @ExcludeThis
        private String fieldExcluded = "fieldExcludedValue";

        private String fieldWithGetterExcluded = "fieldWithGetterExcludedValue";

        private String fieldWithSetterExcluded = "fieldWithSetterExcludedValue";

        public String getFieldIncluded() {
            return fieldIncluded;
        }

        public void setFieldIncluded(String fieldIncluded) {
            this.fieldIncluded = fieldIncluded;
        }

        public String getFieldExcluded() {
            return fieldExcluded;
        }

        public void setFieldExcluded(String fieldExcluded) {
            this.fieldExcluded = fieldExcluded;
        }

        @ExcludeThis
        public String getFieldWithGetterExcluded() {
            return fieldWithGetterExcluded;
        }

        public void setFieldWithGetterExcluded(String fieldWithGetterExcluded) {
            this.fieldWithGetterExcluded = fieldWithGetterExcluded;
        }

        public String getFieldWithSetterExcluded() {
            return fieldWithSetterExcluded;
        }

        @ExcludeThis
        public void setFieldWithSetterExcluded(String fieldWithSetterExcluded) {
            this.fieldWithSetterExcluded = fieldWithSetterExcluded;
        }
    }

    @IncludeElements({"includedAndNull", "includedAndNotNull"})
    @IncludeMatching(IncludeMatcher.NOT_NULL)
    public class IncludeFieldsAndMatchingDummy {

        private String includedAndNull = null;
        private String includedAndNotNull = "includedAndNotNullValue";

        private String notIncludedAndNull = null;
        private String notIncludedAndNotNull = "notIncludedAndNotNullValue";

        public String getIncludedAndNull() {
            return includedAndNull;
        }

        public String getIncludedAndNotNull() {
            return includedAndNotNull;
        }

        public String getNotIncludedAndNull() {
            return notIncludedAndNull;
        }

        public String getNotIncludedAndNotNull() {
            return notIncludedAndNotNull;
        }
    }

    private ExcludeThisDummy excludeThisDummy;

    private IncludeFieldsAndMatchingDummy includeFieldsAndMatchingDummy;

    @Before
    public void setUp() {
        excludeThisDummy = new ExcludeThisDummy();
        includeFieldsAndMatchingDummy = new IncludeFieldsAndMatchingDummy();
    }

    @Test
    public void shouldIncludeElement_GivenExcludeThisedField_ReturnsFalse() throws NoSuchMethodException {
        EserialElement element = createElementGetter(excludeThisDummy, "getFieldExcluded");
        assertFalse(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenGetterAndIgnoreThisedGetter_ReturnsFalse() throws NoSuchMethodException {
        EserialElement element = createElementGetter(excludeThisDummy, "getFieldWithGetterExcluded");
        assertFalse(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenSetterAndIgnoreThisedGetter_ReturnsTrue() throws NoSuchMethodException {
        EserialElement element = createElementSetter(excludeThisDummy, "setFieldWithGetterExcluded", String.class);
        assertTrue(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenGetterAndIgnoreThisedSetter_ReturnsTrue() throws NoSuchMethodException {
        EserialElement element = createElementGetter(excludeThisDummy, "getFieldWithSetterExcluded");
        assertTrue(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenSetterAndIgnoreThisedSetter_ReturnsFalse() throws NoSuchMethodException {
        EserialElement element = createElementSetter(excludeThisDummy, "setFieldWithSetterExcluded", String.class);
        assertFalse(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenFieldWithoutIgnoreThis_ReturnsTrue() throws NoSuchMethodException {
        EserialElement element = createElementGetter(excludeThisDummy, "getFieldIncluded");
        assertTrue(AnnotationUtils.shouldIncludeElement(element));
        element = createElementSetter(excludeThisDummy, "setFieldIncluded", String.class);
        assertTrue(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenIncludedAndNullField_ReturnsTrue() throws NoSuchMethodException {
        EserialElement element = createElementGetter(includeFieldsAndMatchingDummy, "getIncludedAndNull");
        assertTrue(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenIncludedAndNotNullField_ReturnsTrue() throws NoSuchMethodException {
        EserialElement element = createElementGetter(includeFieldsAndMatchingDummy, "getIncludedAndNotNull");
        assertTrue(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenNotIncludedAndNullField_ReturnsFalse() throws NoSuchMethodException{
        EserialElement element = createElementGetter(includeFieldsAndMatchingDummy, "getNotIncludedAndNull");
        assertFalse(AnnotationUtils.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenNotIncludedAndNotNullField_ReturnsTrue() throws NoSuchMethodException {
        EserialElement element = createElementGetter(includeFieldsAndMatchingDummy, "getNotIncludedAndNotNull");
        assertTrue(AnnotationUtils.shouldIncludeElement(element));
    }
}
