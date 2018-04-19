package hu.elte.eserial.model;

import hu.elte.eserial.recursion.RecursionChecker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EserialContextTest {

    @Test(expected = NullPointerException.class)
    public void forRoot_GivenNull_ThrowsNullPointerException() {
        EserialContext.forRoot(null);
    }

    @Test
    public void forRoot_GivenSomeObject_ReturnsAnEserialContextWithContainingClassAndRecursionCheckerSet() {
        EserialContext context = EserialContext.forRoot("Eserial");

        assertNotNull(context);
        assertEquals(String.class, context.getElement().getContainingClass());
        assertNotNull(context.getRecursionChecker());
    }

    public class User {
        private String name;
        public String getName() { return "someName"; }
        public Integer getAge() { return 10; }
    }

    @Test
    public void forMapperElement_GivenEserialElementAndRecursionChecker_SetsThoseProperties() {
        EserialElement element = EserialElement.fromValue(null);
        EserialContext context = EserialContext.forMapperElement(element, new RecursionChecker(null));

        assertNotNull(context.getElement());
        assertNotNull(context.getRecursionChecker());
    }

    @Test
    public void forBuilderElement_GivenEserialElementAndRecursionChecker_SetsThoseProperties() {
        EserialElement element = EserialElement.fromValue(null);
        EserialContext context = EserialContext.forBuilderElement(element);

        assertNotNull(context.getElement());
    }

    @Test
    public void fromValue_GivenAValue_SetsTheValue() {
        assertEquals(5, EserialElement.fromValue(5).getValue());
        assertNull(EserialElement.fromValue(null).getValue());
    }

    @Test
    public void fromAccessor_GivenAnAccessorOfRealField_SetsTheAccessorContainingClassAndField()
            throws NoSuchMethodException, NoSuchFieldException {
        Getter getter = new Getter(new User(), User.class.getDeclaredMethod("getName"));
        EserialElement element = EserialElement.fromAccessor(getter);

        assertEquals(getter, element.getAccessor());
        assertEquals(User.class, element.getContainingClass());
        assertEquals(User.class.getDeclaredField("name"), element.getField());
    }

    @Test
    public void fromAccessor_GivenAnAccessorOfVirtualField_SetsTheAccessorContainingClassButNotField()
            throws NoSuchMethodException {
        Getter getter = new Getter(new User(), User.class.getDeclaredMethod("getAge"));
        EserialElement element = EserialElement.fromAccessor(getter);

        assertEquals(getter, element.getAccessor());
        assertEquals(User.class, element.getContainingClass());
        assertNull(element.getField());
    }

    @Test
    public void fromAccessorAndValue_GivenAnAccessorOfRealFieldAndValue_SetsTheAccessorContainingClassFieldAndValue()
            throws NoSuchMethodException, NoSuchFieldException {
        Getter getter = new Getter(new User(), User.class.getDeclaredMethod("getName"));
        EserialElement element = EserialElement.fromAccessorAndValue(getter, 10);

        assertEquals(getter, element.getAccessor());
        assertEquals(User.class, element.getContainingClass());
        assertEquals(User.class.getDeclaredField("name"), element.getField());
        assertEquals(10, element.getValue());
    }

    @Test
    public void fromAccessorAndValue_GivenAnAccessorOfVirtualFieldAndValue_SetsTheAccessorContainingClassAndValueButNotField()
            throws NoSuchMethodException {
        Getter getter = new Getter(new User(), User.class.getDeclaredMethod("getAge"));
        EserialElement element = EserialElement.fromAccessorAndValue(getter, 10);

        assertEquals(getter, element.getAccessor());
        assertEquals(User.class, element.getContainingClass());
        assertNull(element.getField());
        assertEquals(10, element.getValue());
    }

    @Test
    public void fromContainingClass_GivenAClass_SetsTheContainingClassProperty() {
        assertEquals(User.class, EserialElement.fromContainingClass(User.class).getContainingClass());
    }
}
