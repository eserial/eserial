package hu.elte.eserial.annotation.processor;

import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.testutil.dummy.ExcludeThisDummy;
import hu.elte.eserial.testutil.dummy.IncludeFieldsAndMatchingDummy;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EserialAnnotationProcessorTest {

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
    public void testShouldIncludeElement_GivenExcludeThisedField_ReturnsFalse()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter = ExcludeThisDummy.class.getDeclaredMethod("getFieldExcluded");
        Object value = getter.invoke(this.excludeThisDummy);
        EserialElement element = new EserialElement(getter, value);

        assertFalse(this.annotationProcessor.shouldIncludeElement(element, new HashMap<>()));
    }

    @Test
    public void testShouldIncludeElement_GivenIgnoreThisedGetter_ReturnsFalse()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter = ExcludeThisDummy.class.getDeclaredMethod("getFieldWithGetterExcluded");
        Object value = getter.invoke(this.excludeThisDummy);
        EserialElement element = new EserialElement(getter, value);

        assertFalse(this.annotationProcessor.shouldIncludeElement(element, new HashMap<>()));
    }

    @Test
    public void testShouldIncludeElement_GivenIgnoreThisedSetter_ReturnsFalse() throws NoSuchMethodException {

        Method setter = ExcludeThisDummy.class.getDeclaredMethod("setFieldWithSetterExcluded", String.class);
        Object value = "valueToSet";
        EserialElement element = new EserialElement(setter, value);

        assertFalse(this.annotationProcessor.shouldIncludeElement(element, new HashMap<>()));
    }

    @Test
    public void testShouldIncludeElement_GivenFieldWithoutIgnoreThis_ReturnsTrue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter = ExcludeThisDummy.class.getDeclaredMethod("getFieldIncluded");
        Object value = getter.invoke(this.excludeThisDummy);
        EserialElement element = new EserialElement(getter, value);

        assertTrue(this.annotationProcessor.shouldIncludeElement(element, new HashMap<>()));
    }

    @Test
    public void testShouldIncludeElement_GivenIncludedAndNullField_ReturnsTrue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter = IncludeFieldsAndMatchingDummy.class.getDeclaredMethod("getIncludedAndNull");
        Object value = getter.invoke(this.includeFieldsAndMatchingDummy);
        EserialElement element = new EserialElement(getter, value);

        assertTrue(this.annotationProcessor.shouldIncludeElement(element, new HashMap<>()));
    }

    @Test
    public void testShouldIncludeElement_GivenIncludedAndNotNullField_ReturnsTrue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter = IncludeFieldsAndMatchingDummy.class.getDeclaredMethod("getIncludedAndNotNull");
        Object value = getter.invoke(this.includeFieldsAndMatchingDummy);
        EserialElement element = new EserialElement(getter, value);

        assertTrue(this.annotationProcessor.shouldIncludeElement(element, new HashMap<>()));
    }

    @Test
    public void testShouldIncludeElement_GivenNotIncludedAndNullField_ReturnsFalse()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter = IncludeFieldsAndMatchingDummy.class.getDeclaredMethod("getNotIncludedAndNull");
        Object value = getter.invoke(this.includeFieldsAndMatchingDummy);
        EserialElement element = new EserialElement(getter, value);

        assertFalse(this.annotationProcessor.shouldIncludeElement(element, new HashMap<>()));
    }

    @Test
    public void testShouldIncludeElement_GivenNotIncludedAndNotNullField_ReturnsTrue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter = IncludeFieldsAndMatchingDummy.class.getDeclaredMethod("getNotIncludedAndNotNull");
        Object value = getter.invoke(this.includeFieldsAndMatchingDummy);
        EserialElement element = new EserialElement(getter, value);

        assertTrue(this.annotationProcessor.shouldIncludeElement(element, new HashMap<>()));
    }
}
