package hu.elte.eserial.model;

import hu.elte.eserial.exception.EserialInvalidMethodException;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void getElementName_GivenAGetterThatStartsWithGet_ReturnsId() throws NoSuchMethodException {
            Method getId = ClassWithGetters.class.getDeclaredMethod("getId");

            Getter getter = new Getter(new ClassWithGetters(), getId);
            assertEquals("id", getter.getElementName());
    }

    @Test
    public void getElementName_GivenAGetterThatStartsWithStartsWithIs_ReturnsIs() throws NoSuchMethodException {
            Method isReady = ClassWithGetters.class.getDeclaredMethod("isReady");

            Getter getter = new Getter(new ClassWithGetters(), isReady);
            assertEquals("ready", getter.getElementName());
    }

    @Test(expected = EserialInvalidMethodException.class)
    public void getElementName_GivenAnInvalidGetter_ThrowsEserialInvalidMethodException() throws NoSuchMethodException {
            Method malformedGetter = ClassWithGetters.class.getDeclaredMethod("malformedGetter");

            Getter getter = new Getter(new ClassWithGetters(), malformedGetter);
            getter.getElementName();
    }

    @Test
    public void evaluate_GivenAGetter_ReturnsItsValue() throws NoSuchMethodException {
            Method isReady = ClassWithGetters.class.getDeclaredMethod("isReady");

            Getter getter = new Getter(new ClassWithGetters(), isReady);
            assertTrue((boolean) getter.evaluate());
    }

    @Test(expected = EserialInvalidMethodException.class)
    public void evaluate_GivenAnInvalidGetter_ThrowsEserialInvalidMethodException() throws NoSuchMethodException {
            Method getterThatThrows = ClassWithGetters.class.getDeclaredMethod("getterThatThrows");

            Getter getter = new Getter(new ClassWithGetters(), getterThatThrows);
            getter.evaluate();
    }
}