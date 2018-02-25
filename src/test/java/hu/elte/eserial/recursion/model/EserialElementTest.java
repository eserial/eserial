package hu.elte.eserial.recursion.model;

import hu.elte.eserial.testutil.dummy.BasicUser;
import hu.elte.eserial.testutil.dummy.BasicUserCopy;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EserialElementTest {

    private BasicUser basicUser;

    @Before
    public void setUp() {
        basicUser = new BasicUser();
        basicUser.setName("nameValue");
        basicUser.setAge(20);
    }

    @Test
    public void testEquals_GivenSameElements_AreEqual()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = new EserialElement(getter1, value1);

        Method getter2 = BasicUser.class.getDeclaredMethod("getName");
        Object value2 = getter1.invoke(this.basicUser);
        EserialElement element2 = new EserialElement(getter2, value2);

        assertEquals(element1, element2);
    }

    @Test
    public void testEquals_GivenSameGetterAndDifferentValues_AreNotEqual()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = new EserialElement(getter1, value1);

        this.basicUser.setName("anotherName");
        Method getter2 = BasicUser.class.getDeclaredMethod("getName");
        Object value2 = getter2.invoke(this.basicUser);
        EserialElement element2 = new EserialElement(getter2, value2);

        assertNotEquals(element1, element2);
    }

    @Test
    public void testEquals_GivenSameGetterAndDifferentReferenceButSameValueValues_AreNotEqual()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = new EserialElement(getter1, value1);

        Method getter2 = BasicUser.class.getDeclaredMethod("getName");
        Object value2 = new String((String)value1);
        EserialElement element2 = new EserialElement(getter2, value2);

        assertNotEquals(element1, element2);
    }

    @Test
    public void testEquals_GivenDifferentGetterAndSameValue_AreNotEqual()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String value = "commonNameValue";
        this.basicUser.setName(value);
        Method getter1 = BasicUser.class.getDeclaredMethod("getName");
        Object value1 = getter1.invoke(this.basicUser);
        EserialElement element1 = new EserialElement(getter1, value1);

        BasicUserCopy basicUserCopy = new BasicUserCopy();
        basicUserCopy.setName(value);
        Method getter2 = BasicUserCopy.class.getDeclaredMethod("getName");
        Object value2 = getter2.invoke(basicUserCopy);
        EserialElement element2 = new EserialElement(getter2, value2);

        assertNotEquals(element1, element2);
    }
}
