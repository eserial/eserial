package hu.elte.eserial.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EserialElementTest {

    public class BasicUser {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    private BasicUser basicUser;

    private EserialElement createElement(Method method, Object value) {
        return EserialElement.fromAccessorAndValue(new Getter(basicUser, method), value);
    }

    @Before
    public void setUp() {
        basicUser = new BasicUser();
        basicUser.setName("nameValue");
        basicUser.setAge(20);
    }

    @Test
    public void equals_GivenThisWithNullAccessor_ReturnsFalse()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = createElement(getter1, value1);

        EserialElement element2 = EserialElement.fromValue(value1);
        element2.setAccessor(new Getter(basicUser, null));
        assertFalse(element1.equalsForRecursion(element2));
    }

    @Test
    public void equals_GivenSameElements_AreEqual()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = createElement(getter1, value1);

        Method getter2 = BasicUser.class.getDeclaredMethod("getName");
        Object value2 = getter1.invoke(this.basicUser);
        EserialElement element2 = createElement(getter2, value2);

        assertTrue(element1.equalsForRecursion(element2));
    }

    @Test
    public void equals_GivenSameGetterAndDifferentValues_AreNotEqual()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = createElement(getter1, value1);

        this.basicUser.setName("anotherName");
        Method getter2 = BasicUser.class.getDeclaredMethod("getName");
        Object value2 = getter2.invoke(this.basicUser);
        EserialElement element2 = createElement(getter2, value2);

        assertFalse(element1.equalsForRecursion(element2));
    }

    @Test
    public void equals_GivenSameGetterAndDifferentReferenceButSameValueValues_AreNotEqual()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = createElement(getter1, value1);

        Method getter2 = BasicUser.class.getDeclaredMethod("getName");
        Object value2 = new String((String)value1);
        EserialElement element2 = createElement(getter2, value2);

        assertFalse(element1.equalsForRecursion(element2));
    }

    public class BasicUserCopy {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    @Test
    public void equals_GivenDifferentGetterAndSameValue_AreNotEqual()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String value = "commonNameValue";
        this.basicUser.setName(value);
        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = createElement(getter1, value1);

        BasicUserCopy basicUserCopy = new BasicUserCopy();
        basicUserCopy.setName(value);
        Method getter2 = BasicUserCopy.class.getDeclaredMethod("getName");
        Object value2 = getter2.invoke(basicUserCopy);
        EserialElement element2 = createElement(getter2, value2);

        assertFalse(element1.equalsForRecursion(element2));
    }
}
