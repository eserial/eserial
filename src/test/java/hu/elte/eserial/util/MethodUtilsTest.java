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
    public void isGetter_hasNoParameters() {
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
    public void isGetter_startsWithLowercaseGet() {
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
    public void isGetter_capitalAfterGet() {
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
    public void isGetter_returnsNonVoidIfStartsWithGet() {
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
    public void isGetter_startsWithLowercaseIs() {
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
    public void isGetter_capitalAfterIs() {
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
    public void isGetter_returnsBooleanIfStartsWithIs() {
        try {
            Method isReadyReturnsInteger = GetterCandidates.class.getDeclaredMethod("isReadyReturnsInteger");
            assertFalse(MethodUtils.isGetter(isReadyReturnsInteger));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isIgnored_builtInGetClass() {
        try {
            Method getClass = GetterCandidates.class.getMethod("getClass");
            assertTrue(MethodUtils.isIgnored(getClass));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isIgnored_normalGetter() {
        try {
            Method getId = GetterCandidates.class.getDeclaredMethod("getId");
            assertFalse(MethodUtils.isIgnored(getId));
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }
}