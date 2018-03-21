package hu.elte.eserial.model;

import hu.elte.eserial.exception.EserialInvalidMethodException;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SetterTest {
    public class ClassWithSetters {
        private int id;

        public void setId(int id) {
            this.id = id;
        }

        public void invalidSet(int id) {
            this.id = id;
        }

        void setterThatThrows(int id) throws Exception {
            throw new Exception();
        }

        public int getId() {
            return id;
        }
    }

    @Test(expected = EserialInvalidMethodException.class)
    public void getElementName_GivenAnInValidSetter_ThrowsEserialInvalidMethodException() {
        try {
            Method method = ClassWithSetters.class.getDeclaredMethod("invalidSet", int.class);

            Setter setter = new Setter(new ClassWithSetters(), method);
            setter.getElementName();
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getElementName_GivenASetterThatStartsWithSet_ReturnsId() {
        try {
            Method method = ClassWithSetters.class.getDeclaredMethod("setId", int.class);

            Setter setter = new Setter(new ClassWithSetters(), method);
            assertEquals("id", setter.getElementName());
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = EserialInvalidMethodException.class)
    public void evaluate_GivenAnInvalidSetter_ThrowsEserialInvalidMethodException() {
        try {
            Method method = ClassWithSetters.class.getDeclaredMethod("setterThatThrows", int.class);

            Setter setter = new Setter(new ClassWithSetters(), method);
            setter.evaluate(2);
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void evaluate_GivenASetter_SetsItsValue() {
        try {
            Method method = ClassWithSetters.class.getDeclaredMethod("setId", int.class);

            ClassWithSetters classWithSetters = new ClassWithSetters();
            Setter setter = new Setter(classWithSetters, method);
            setter.evaluate(2);
            assertEquals(2, classWithSetters.getId());
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getTypeParameter_GivenInt_ReturnsIntType() {
        try {
            Method method = ClassWithSetters.class.getDeclaredMethod("setId", int.class);

            Setter setter = new Setter(new ClassWithSetters(), method);

            assertEquals(int.class, setter.getTypeParameter());
        } catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }
}
