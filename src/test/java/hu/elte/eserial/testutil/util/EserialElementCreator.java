package hu.elte.eserial.testutil.util;

import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.testutil.dummy.BasicUser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EserialElementCreator {

    public static EserialElement withDummyGetter(Object value) {
        try {
            return new EserialElement(BasicUser.class.getDeclaredMethod("getName"), value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static EserialElement withNamedGetter(String getterName, Object value) {
        Method getter = null;
        try {
            getter = BasicUser.class.getDeclaredMethod(getterName);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new EserialElement(getter, value);
    }

    public static EserialElement fromInstance(Object instance, String getterName) {
        try {
            Method getter = instance.getClass().getDeclaredMethod(getterName);
            Object value = getter.invoke(instance);
            return new EserialElement(getter, value);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
