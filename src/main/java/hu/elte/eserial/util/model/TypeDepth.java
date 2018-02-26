package hu.elte.eserial.util.model;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Represents a linear generic type hierarchy with the innermost type and its depth.
 */
public class TypeDepth {

  private Type type;
  private int depth;

  public TypeDepth(Type type, int depth) {
    this.type = type;
    this.depth = depth;
  }

  public Type getType() {
    return type;
  }

  public void setType(Class type) {
    this.type = type;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TypeDepth typeDepth = (TypeDepth)o;
    return depth == typeDepth.depth &&
        Objects.equals(type, typeDepth.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, depth);
  }

  @Override
  public String toString() {
    return "TypeDepth{" +
        "type=" + type +
        ", depth=" + depth +
        '}';
  }
}
