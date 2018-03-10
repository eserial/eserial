package hu.elte.eserial.model;

import hu.elte.eserial.recursion.RecursionChecker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EserialContextTest {

    @Test(expected = NullPointerException.class)
    public void forRoot_GivenNull_ThrowsNullPointerException() {
        EserialContext.forRoot(null);
    }

    @Test
    public void forRoot_GivenSomeObject_ReturnsAnEserialContextWithContainingClassAndRecursionCheckerSet() {
        EserialContext context = EserialContext.forRoot("Eserial");

        assertNotNull(context);
        assertEquals(context.getContainingClass(), String.class);
        assertNotNull(context.getRecursionChecker());
    }

    public class User {
        private String name;
        public String getName() { return "someName"; }
        public Integer getAge() { return 10; }
    }

    @Test(expected = NullPointerException.class)
    public void forElement_GivenNullObject_ThrowsNullPointerException() {
        EserialContext.forElement(null, "getName", null);
    }

    @Test
    public void forElement_GivenSomeObjectAndAGetter_ReturnsAnEserialContextWithEverythingSet() {
        User user = new User();
        EserialContext context = EserialContext.forElement(user, "getName", new RecursionChecker(user));

        assertNotNull(context);
        assertNotNull(context.getGetter());
        assertEquals(context.getGetter().getMethod().getName(), "getName");
        assertNotNull(context.getField());
        assertEquals(context.getField().getName(), "name");
        assertEquals(context.getContainingClass(), User.class);
        assertNotNull(context.getRecursionChecker());
    }
}
