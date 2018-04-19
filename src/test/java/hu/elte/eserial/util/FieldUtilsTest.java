package hu.elte.eserial.util;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FieldUtilsTest {

    @Test(expected = NullPointerException.class)
    public void getField_GivenNullClass_ThrowsNullPointerException() {
        FieldUtils.getField(null, "");
    }

    public class ClassWithField {
        public String aField;
    }

    @Test(expected = NullPointerException.class)
    public void getField_GivenNullFieldName_ThrowsNullPointerException() {
        assertNull(FieldUtils.getField(ClassWithField.class, null));
    }

    @Test
    public void getField_GivenInvalidFieldName_ReturnsNull() {
        assertNull(FieldUtils.getField(ClassWithField.class, "invalidField"));
    }

    @Test
    public void getField_GivenValidFieldName_ReturnsNonNull() {
        assertNotNull(FieldUtils.getField(ClassWithField.class, "aField"));
    }
}