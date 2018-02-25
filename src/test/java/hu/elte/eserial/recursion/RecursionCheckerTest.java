package hu.elte.eserial.recursion;

import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.testutil.dummy.SingleEdge;
import hu.elte.eserial.testutil.dummy.SingleNode;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hu.elte.eserial.testutil.util.EserialElementCreator.fromInstance;
import static hu.elte.eserial.testutil.util.EserialElementCreator.withDummyGetter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RecursionCheckerTest {

    private SingleNode node1;
    private SingleNode node2;

    private SingleEdge edge;

    private RecursionChecker recursionChecker;

    private List<EserialElement> getElements() {
        try {
            Field elementsField = RecursionChecker.class.getDeclaredField("elements");
            elementsField.setAccessible(true);
            return (List<EserialElement>) elementsField.get(this.recursionChecker);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setElements(EserialElement... elements) {
        try {
            Field elementsField = RecursionChecker.class.getDeclaredField("elements");
            elementsField.setAccessible(true);
            elementsField.set(this.recursionChecker, new ArrayList<>(Arrays.asList(elements)));
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        this.node1 = new SingleNode(1, "node1Name");
        this.node2 = new SingleNode(2, "node2Name");
        this.edge = new SingleEdge(0, "edgeName", node1, node2);
        this.node1.setEdge(edge);
        this.node2.setEdge(edge);

        this.recursionChecker = new RecursionChecker(this.node1);
    }

    @Test
    public void testBeforeVisit_GivenAnything_PutsIntoElementsList() {

        EserialElement element = withDummyGetter(null);
        this.recursionChecker.beforeVisit(element);

        assertEquals(1, this.getElements().size());
    }

    @Test
    public void testAfterVisit_GivenPreviousElement_RemovesItAndProceedingElements() {

        EserialElement nodeIdElement = fromInstance(this.node1, "getNodeId");
        EserialElement nodeNameElement = fromInstance(this.node1, "getNodeName");
        EserialElement edgeElement = fromInstance(this.node1, "getEdge");
        EserialElement edgeIdElement = fromInstance(this.node1.getEdge(), "getEdgeId");
        EserialElement edgeNameElement = fromInstance(this.node1.getEdge(), "getEdgeName");

        setElements(nodeIdElement, nodeNameElement, edgeElement, edgeIdElement, edgeNameElement);

        this.recursionChecker.afterVisit(edgeElement);
        assertEquals(2, this.getElements().size());
    }

    @Test
    public void testCanVisit_GivenNonRecursiveElement_ReturnsTrue() {
        EserialElement nodeIdElement = fromInstance(this.node1, "getNodeId");
        EserialElement nodeNameElement = fromInstance(this.node1, "getNodeName");

        setElements(nodeIdElement, nodeNameElement);

        EserialElement edgeElement = fromInstance(this.node1, "getEdge");
        assertTrue(this.recursionChecker.canVisit(edgeElement));
    }

    @Test
    public void testCanVisit_GivenRootRecursiveElement_ReturnsFalse() {
        EserialElement nodeIdElement = fromInstance(this.node1, "getNodeId");
        EserialElement nodeNameElement = fromInstance(this.node1, "getNodeName");
        EserialElement edgeElement = fromInstance(this.node1, "getEdge");

        setElements(nodeIdElement, nodeNameElement, edgeElement);

        EserialElement node1Element = fromInstance(this.node1.getEdge(), "getNode1");
        assertFalse(this.recursionChecker.canVisit(node1Element));
    }

    @Test
    public void testCanVisit_GivenIntermediateRecursiveElement_ReturnsFalse() {

        EserialElement node1IdElement = fromInstance(this.node1, "getNodeId");
        EserialElement node1NameElement = fromInstance(this.node1, "getNodeName");
        EserialElement edgeInNode1Element = fromInstance(this.node1, "getEdge");
        EserialElement node2IdElement = fromInstance(this.node2, "getNodeId");
        EserialElement node2NameElement = fromInstance(this.node2, "getNodeName");

        setElements(node1IdElement, node1NameElement, edgeInNode1Element, node2IdElement, node2NameElement);

        EserialElement edgeInNode2Element = fromInstance(this.node2, "getEdge");
        assertFalse(this.recursionChecker.canVisit(edgeInNode2Element));
    }
}
