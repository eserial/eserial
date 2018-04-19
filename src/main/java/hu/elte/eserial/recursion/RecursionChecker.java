package hu.elte.eserial.recursion;

import hu.elte.eserial.recursion.model.EserialElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple class to check for recursion while serializing an object.<br>
 * Usage:<br>
 * <ol>
 *     <li>While recursively visiting the object's elements, before each step the
 *     {@link RecursionChecker#beforeVisit} should be called with the element to visit.</li>
 *     <li>After this call, the {@link RecursionChecker#canVisit} tells whether this
 *     element is allowed to be visited.</li>
 *     <li>After the visit, the {@link RecursionChecker#afterVisit} should be called to
 *     signal the end of the visit.</li>
 * </ol>
 */
public class RecursionChecker {

  private List<EserialElement> elements = new ArrayList<>();
  private Object root;

  public RecursionChecker(Object root) {
    this.root = root;
  }

  /**
   * @param element the element to check before visiting it
   * @return {@code true} if the {@code element} has not already been visited and it's not the root of the map.
   */
  public boolean canVisit(EserialElement element) {
    return !this.elements.contains(element)
            && element.getValue() != this.root;
  }

  /**
   * Saves the element for later checking of possible recursions.
   * @param element the element to be visited
   */
  public void beforeVisit(EserialElement element) {
    this.elements.add(element);
  }

  /**
   * Removes the {@code element} and all of its children from the recursion checking.
   * @param element the element that has been visited
   */
  public void afterVisit(EserialElement element) {
    int index = this.elements.indexOf(element);
    if (index != -1) {
      for (int i = this.elements.size() - 1; i >= index; i--) {
        this.elements.remove(i);
      }
    }
  }
}
