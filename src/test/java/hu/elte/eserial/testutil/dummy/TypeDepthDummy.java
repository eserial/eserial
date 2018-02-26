package hu.elte.eserial.testutil.dummy;

import java.util.List;
import java.util.Map;

public class TypeDepthDummy {
  private String string;

  private List<Integer> integerList;
  private List<BasicUser> basicUserList;
  private List<List<Integer>> integerListList;
  private List<List<BasicUser>> basicUserListList;

  private Map<String, String> stringStringMap;
  private Map<String, BasicUser> stringBasicUserListMap;

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public List<Integer> getIntegerList() {
    return integerList;
  }

  public void setIntegerList(List<Integer> integerList) {
    this.integerList = integerList;
  }

  public List<BasicUser> getBasicUserList() {
    return basicUserList;
  }

  public void setBasicUserList(List<BasicUser> basicUserList) {
    this.basicUserList = basicUserList;
  }

  public List<List<Integer>> getIntegerListList() {
    return integerListList;
  }

  public void setIntegerListList(List<List<Integer>> integerListList) {
    this.integerListList = integerListList;
  }

  public List<List<BasicUser>> getBasicUserListList() {
    return basicUserListList;
  }

  public void setBasicUserListList(List<List<BasicUser>> basicUserListList) {
    this.basicUserListList = basicUserListList;
  }

  public Map<String, String> getStringStringMap() {
    return stringStringMap;
  }

  public void setStringStringMap(Map<String, String> stringStringMap) {
    this.stringStringMap = stringStringMap;
  }

  public Map<String, BasicUser> getStringBasicUserListMap() {
    return stringBasicUserListMap;
  }

  public void setStringBasicUserListMap(
      Map<String, BasicUser> stringBasicUserListMap) {
    this.stringBasicUserListMap = stringBasicUserListMap;
  }
}
