package hu.elte.eserial.annotation.processor;

import hu.elte.eserial.annotation.ExcludeThis;
import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;
import hu.elte.eserial.recursion.model.EserialElement;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EserialAnnotationProcessorTest {

    public class ExcludeThisDummy {

        private String fieldIncluded = "fieldIncludedValue";

        @ExcludeThis
        private String fieldExcluded = "fieldExcludedValue";

        private String fieldWithGetterExcluded = "fieldWithGetterExcludedValue";

        public String getFieldIncluded() {
            return fieldIncluded;
        }

        public String getFieldExcluded() {
            return fieldExcluded;
        }

        @ExcludeThis
        public String getFieldWithGetterExcluded() {
            return fieldWithGetterExcluded;
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

    private EserialAnnotationProcessor annotationProcessor;

    @Before
    public void setUp() {
        excludeThisDummy = new ExcludeThisDummy();
        includeFieldsAndMatchingDummy = new IncludeFieldsAndMatchingDummy();
        annotationProcessor = new EserialAnnotationProcessor();
    }

    @Test
    public void shouldIncludeElement_GivenExcludeThisedField_ReturnsFalse()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter = ExcludeThisDummy.class.getDeclaredMethod("getFieldExcluded");
        Object value = getter.invoke(this.excludeThisDummy);
        EserialElement element = new EserialElement(getter, value);

        assertFalse(this.annotationProcessor.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenIgnoreThisedGetter_ReturnsFalse()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter = ExcludeThisDummy.class.getDeclaredMethod("getFieldWithGetterExcluded");
        Object value = getter.invoke(this.excludeThisDummy);
        EserialElement element = new EserialElement(getter, value);

        assertFalse(this.annotationProcessor.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenFieldWithoutIgnoreThis_ReturnsTrue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter = ExcludeThisDummy.class.getDeclaredMethod("getFieldIncluded");
        Object value = getter.invoke(this.excludeThisDummy);
        EserialElement element = new EserialElement(getter, value);

        assertTrue(this.annotationProcessor.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenIncludedAndNullField_ReturnsTrue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter = IncludeFieldsAndMatchingDummy.class.getDeclaredMethod("getIncludedAndNull");
        Object value = getter.invoke(this.includeFieldsAndMatchingDummy);
        EserialElement element = new EserialElement(getter, value);

        assertTrue(this.annotationProcessor.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenIncludedAndNotNullField_ReturnsTrue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter = IncludeFieldsAndMatchingDummy.class.getDeclaredMethod("getIncludedAndNotNull");
        Object value = getter.invoke(this.includeFieldsAndMatchingDummy);
        EserialElement element = new EserialElement(getter, value);

        assertTrue(this.annotationProcessor.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenNotIncludedAndNullField_ReturnsFalse()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter = IncludeFieldsAndMatchingDummy.class.getDeclaredMethod("getNotIncludedAndNull");
        Object value = getter.invoke(this.includeFieldsAndMatchingDummy);
        EserialElement element = new EserialElement(getter, value);

        assertFalse(this.annotationProcessor.shouldIncludeElement(element));
    }

    @Test
    public void shouldIncludeElement_GivenNotIncludedAndNotNullField_ReturnsTrue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter = IncludeFieldsAndMatchingDummy.class.getDeclaredMethod("getNotIncludedAndNotNull");
        Object value = getter.invoke(this.includeFieldsAndMatchingDummy);
        EserialElement element = new EserialElement(getter, value);

        assertTrue(this.annotationProcessor.shouldIncludeElement(element));
    }
}
