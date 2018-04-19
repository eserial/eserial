package hu.elte.eserial.util;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MethodUtilsTest {

    abstract class GetterCandidates {
        abstract Integer get();
        abstract Integer getId();
        abstract Integer getIdWithSomeParam(Object someParam);
        abstract void getIdReturnsVoid();
        abstract Integer getid();
        abstract Integer GetId();
        abstract Boolean is();
        abstract Boolean isReady();
        abstract Integer isReadyReturnsInteger();
        abstract Boolean isReadyWithSomeParam(Object someParam);
        abstract Boolean isready();
        abstract Boolean IsReady();
    }

    @Test
    public void isGetter_GivenAGetterWithParameters_ReturnsFalse() {
        try {
            Method getIdWithSomeParam = GetterCandidates.class.getDeclaredMethod("getIdWithSomeParam", Object.class);
            assertFalse(MethodUtils.isGetter(getIdWithSomeParam));

            Method isReadyWithSomeParam = GetterCandidates.class.getDeclaredMethod("isReadyWithSomeParam", Object.class);
            assertFalse(MethodUtils.isGetter(isReadyWithSomeParam));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isGetter_GivenAGetterThatStartsWithLowercaseGet_ReturnsTrue() {
        try {
            Method getId = GetterCandidates.class.getDeclaredMethod("getId");
            assertTrue(MethodUtils.isGetter(getId));

            Method GetId = GetterCandidates.class.getDeclaredMethod("GetId");
            assertFalse(MethodUtils.isGetter(GetId));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isGetter_GivenAGetterThatHasACapitalAfterGet_ReturnsTrue() {
        try {
            Method get = GetterCandidates.class.getDeclaredMethod("get");
            assertFalse(MethodUtils.isGetter(get));

            Method getid = GetterCandidates.class.getDeclaredMethod("getid");
            assertFalse(MethodUtils.isGetter(getid));

            Method getId = GetterCandidates.class.getDeclaredMethod("getId");
            assertTrue(MethodUtils.isGetter(getId));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isGetter_GivenAGetterThatReturnsNonVoidIfStartsWithGet_ReturnsTrue() {
        try {
            Method getIdReturnsVoid = GetterCandidates.class.getDeclaredMethod("getIdReturnsVoid");
            assertFalse(MethodUtils.isGetter(getIdReturnsVoid));

            Method getId = GetterCandidates.class.getDeclaredMethod("getId");
            assertTrue(MethodUtils.isGetter(getId));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isGetter_GivenAGetterThatStartsWithLowercaseIs_ReturnsTrue() {
        try {
            Method isReady = GetterCandidates.class.getDeclaredMethod("isReady");
            assertTrue(MethodUtils.isGetter(isReady));

            Method IsReady = GetterCandidates.class.getDeclaredMethod("IsReady");
            assertFalse(MethodUtils.isGetter(IsReady));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isGetter_GivenAGetterThatHasACapitalAfterIs_ReturnsTrue() {
        try {
            Method is = GetterCandidates.class.getDeclaredMethod("is");
            assertFalse(MethodUtils.isGetter(is));

            Method isready = GetterCandidates.class.getDeclaredMethod("isready");
            assertFalse(MethodUtils.isGetter(isready));

            Method isReady = GetterCandidates.class.getDeclaredMethod("isReady");
            assertTrue(MethodUtils.isGetter(isReady));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isGetter_GivenAGetterThatReturnsBooleanIfStartsWithIs_ReturnsTrue() {
        try {
            Method isReadyReturnsInteger = GetterCandidates.class.getDeclaredMethod("isReadyReturnsInteger");
            assertFalse(MethodUtils.isGetter(isReadyReturnsInteger));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isIgnored_GivenTheBuildInMethodGetClass_ReturnsTrue() {
        try {
            Method getClass = GetterCandidates.class.getMethod("getClass");
            assertTrue(MethodUtils.isIgnored(getClass));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isIgnored_GivenANormalGetter_ReturnsFalse() {
        try {
            Method getId = GetterCandidates.class.getDeclaredMethod("getId");
            assertFalse(MethodUtils.isIgnored(getId));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = NullPointerException.class)
    public void getField_GivenNullClass_ThrowsNullPointerException() {
        FieldUtils.getField(null, "");
    }

    public abstract class ClassWithMethod {
        public abstract String aMethod();
    }

    @Test(expected = NullPointerException.class)
    public void getField_GivenNullMethodName_ThrowsNullPointerException() {
        assertNull(MethodUtils.getMethod(ClassWithMethod.class, null));
    }

    @Test
    public void getField_GivenInvalidMethodName_ReturnsNull() {
        assertNull(MethodUtils.getMethod(ClassWithMethod.class, "invalidMethod"));
    }

    @Test
    public void getField_GivenValidMethodName_ReturnsNonNull() {
        assertNotNull(MethodUtils.getMethod(ClassWithMethod.class, "aMethod"));
    }
}
