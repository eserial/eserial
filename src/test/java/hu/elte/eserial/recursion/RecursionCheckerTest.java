package hu.elte.eserial.recursion;

import hu.elte.eserial.recursion.model.EserialElement;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RecursionCheckerTest {

    public class SingleEdge {

        private Integer edgeId;
        private String edgeName;
        private SingleNode node1;
        private SingleNode node2;

        public SingleEdge(Integer edgeId, String edgeName, SingleNode node1, SingleNode node2) {
            this.edgeId = edgeId;
            this.edgeName = edgeName;
            this.node1 = node1;
            this.node2 = node2;
        }

        public String getEdgeName() {
            return edgeName;
        }

        public void setEdgeName(String edgeName) {
            this.edgeName = edgeName;
        }

        public Integer getEdgeId() {
            return edgeId;
        }

        public void setEdgeId(Integer edgeId) {
            this.edgeId = edgeId;
        }

        public SingleNode getNode1() {
            return node1;
        }

        public void setNode1(SingleNode node1) {
            this.node1 = node1;
        }

        public SingleNode getNode2() {
            return node2;
        }

        public void setNode2(SingleNode node2) {
            this.node2 = node2;
        }
    }

    public class SingleNode {

        private Integer nodeId;
        private String nodeName;
        private SingleEdge edge;

        public SingleNode(Integer nodeId, String nodeName) {
            this.nodeId = nodeId;
            this.nodeName = nodeName;
        }

        public Integer getNodeId() {
            return nodeId;
        }

        public void setNodeId(Integer nodeId) {
            this.nodeId = nodeId;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public SingleEdge getEdge() {
            return edge;
        }

        public void setEdge(SingleEdge edge) {
            this.edge = edge;
        }
    }

    private EserialElement eserialElementFromInstance(Object instance, String getterName) {
        try {
            Method getter = instance.getClass().getDeclaredMethod(getterName);
            Object value = getter.invoke(instance);
            return new EserialElement(getter, value);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

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
    public void beforeVisit_GivenAnything_PutsIntoElementsList() {

        EserialElement element = new EserialElement(null, null);
        this.recursionChecker.beforeVisit(element);

        assertEquals(1, this.getElements().size());
    }

    @Test
    public void afterVisit_GivenPreviousElement_RemovesItAndProceedingElements() {

        EserialElement nodeIdElement = eserialElementFromInstance(this.node1, "getNodeId");
        EserialElement nodeNameElement = eserialElementFromInstance(this.node1, "getNodeName");
        EserialElement edgeElement = eserialElementFromInstance(this.node1, "getEdge");
        EserialElement edgeIdElement = eserialElementFromInstance(this.node1.getEdge(), "getEdgeId");
        EserialElement edgeNameElement = eserialElementFromInstance(this.node1.getEdge(), "getEdgeName");

        setElements(nodeIdElement, nodeNameElement, edgeElement, edgeIdElement, edgeNameElement);

        this.recursionChecker.afterVisit(edgeElement);
        assertEquals(2, this.getElements().size());
    }

    @Test
    public void canVisit_GivenNonRecursiveElement_ReturnsTrue() {
        EserialElement nodeIdElement = eserialElementFromInstance(this.node1, "getNodeId");
        EserialElement nodeNameElement = eserialElementFromInstance(this.node1, "getNodeName");

        setElements(nodeIdElement, nodeNameElement);

        EserialElement edgeElement = eserialElementFromInstance(this.node1, "getEdge");
        assertTrue(this.recursionChecker.canVisit(edgeElement));
    }

    @Test
    public void canVisit_GivenRootRecursiveElement_ReturnsFalse() {
        EserialElement nodeIdElement = eserialElementFromInstance(this.node1, "getNodeId");
        EserialElement nodeNameElement = eserialElementFromInstance(this.node1, "getNodeName");
        EserialElement edgeElement = eserialElementFromInstance(this.node1, "getEdge");

        setElements(nodeIdElement, nodeNameElement, edgeElement);

        EserialElement node1Element = eserialElementFromInstance(this.node1.getEdge(), "getNode1");
        assertFalse(this.recursionChecker.canVisit(node1Element));
    }

    @Test
    public void canVisit_GivenIntermediateRecursiveElement_ReturnsFalse() {

        EserialElement node1IdElement = eserialElementFromInstance(this.node1, "getNodeId");
        EserialElement node1NameElement = eserialElementFromInstance(this.node1, "getNodeName");
        EserialElement edgeInNode1Element = eserialElementFromInstance(this.node1, "getEdge");
        EserialElement node2IdElement = eserialElementFromInstance(this.node2, "getNodeId");
        EserialElement node2NameElement = eserialElementFromInstance(this.node2, "getNodeName");

        setElements(node1IdElement, node1NameElement, edgeInNode1Element, node2IdElement, node2NameElement);

        EserialElement edgeInNode2Element = eserialElementFromInstance(this.node2, "getEdge");
        assertFalse(this.recursionChecker.canVisit(edgeInNode2Element));
    }
}
