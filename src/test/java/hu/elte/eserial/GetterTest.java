package hu.elte.eserial;

import hu.elte.eserial.exception.EserialInvalidMethodException;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class GetterTest {
    public class ClassWithGetters {
        public Integer getId() {
            return 1;
        }

        public boolean isReady() {
            return true;
        }

        public boolean malformedGetter() {
            return false;
        }

        boolean getterThatThrows() throws Exception {
            throw new Exception();
        }
    }

    @Test
    public void getElementName_startsWithGet() {
        try {
            Method getId = ClassWithGetters.class.getDeclaredMethod("getId");

            Getter getter = new Getter(new ClassWithGetters(), getId);
            assertEquals("id", getter.getElementName());
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getElementName_startsWithIs() {
        try {
            Method isReady = ClassWithGetters.class.getDeclaredMethod("isReady");

            Getter getter = new Getter(new ClassWithGetters(), isReady);
            assertEquals("ready", getter.getElementName());
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = EserialInvalidMethodException.class)
    public void getElementName_throwsIfNotAGetter() {
        try {
            Method malformedGetter = ClassWithGetters.class.getDeclaredMethod("malformedGetter");

            Getter getter = new Getter(new ClassWithGetters(), malformedGetter);
            getter.getElementName();
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void evaluate_withValidGetter() {
        try {
            Method isReady = ClassWithGetters.class.getDeclaredMethod("isReady");

            Getter getter = new Getter(new ClassWithGetters(), isReady);
            assertTrue((boolean) getter.evaluate());
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = EserialInvalidMethodException.class)
    public void evaluate_withInvalidGetter() {
        try {
            Method getterThatThrows = ClassWithGetters.class.getDeclaredMethod("getterThatThrows");

            Getter getter = new Getter(new ClassWithGetters(), getterThatThrows);
            getter.evaluate();
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }
}