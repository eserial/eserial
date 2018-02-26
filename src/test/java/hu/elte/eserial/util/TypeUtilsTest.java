package hu.elte.eserial.util;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Type;

import org.junit.Test;

import hu.elte.eserial.testutil.dummy.BasicUser;
import hu.elte.eserial.testutil.dummy.TypeDepthDummy;
import hu.elte.eserial.util.model.TypeDepth;

public class TypeUtilsTest {

  private Type getFromDummy(String getterName) throws NoSuchMethodException {
    return TypeDepthDummy.class.getDeclaredMethod(getterName).getGenericReturnType();
  }

  @Test
  public void testGetTypeDepth_GivenSimpleType_Returns0AndItself() throws NoSuchMethodException {
    Type type = getFromDummy("getString");
    assertEquals(new TypeDepth(String.class, 0), TypeUtils.getTypeDepth(type));
  }

  @Test
  public void testGetTypeDepth_GivenListOfSimpleType_Returns1AndTheType() throws NoSuchMethodException {
    Type type = getFromDummy("getIntegerList");
    assertEquals(new TypeDepth(Integer.class, 1), TypeUtils.getTypeDepth(type));
  }

  @Test
  public void testGetTypeDepth_GivenListOfListOfSimpleType_Returns2AndTheType() throws NoSuchMethodException {
    Type type = getFromDummy("getIntegerListList");
    assertEquals(new TypeDepth(Integer.class, 2), TypeUtils.getTypeDepth(type));
  }

  @Test
  public void testGetTypeDepth_GivenListOfCompoundType_Returns1AndTheType() throws NoSuchMethodException {
    Type type = getFromDummy("getBasicUserList");
    assertEquals(new TypeDepth(BasicUser.class, 1), TypeUtils.getTypeDepth(type));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetTypeDepth_GivenMapOfSimpleTypes_ThrowsIllegalArgumentException() throws NoSuchMethodException {
    Type type = getFromDummy("getStringStringMap");
    TypeUtils.getTypeDepth(type);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetTypeDepth_GivenMapOfSimpleAndCompundTypes_ThrowsIllegalArgumentException() throws NoSuchMethodException {
    Type type = getFromDummy("getStringBasicUserListMap");
    TypeUtils.getTypeDepth(type);
  }
}